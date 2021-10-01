package com.restapi.Restfull.API.Server.controller;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Artist;
import com.restapi.Restfull.API.Server.models.Board;
import com.restapi.Restfull.API.Server.models.BoardComment;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.ArtistService;
import com.restapi.Restfull.API.Server.services.BoardService;
import com.restapi.Restfull.API.Server.services.CDNService;
import com.restapi.Restfull.API.Server.utility.FileConverter;
import com.restapi.Restfull.API.Server.utility.Format;
import com.restapi.Restfull.API.Server.utility.Time;
import com.restapi.Restfull.API.Server.utility.URLConverter;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Log4j2
@RestController
public class BoardController {
    @Autowired
    private BoardService boardService;

    @Autowired
    private ArtistService artistService;

    @Value("${uploadPath}")
    private String upload_path;

    @Value("${cdnPath}")
    private String cdn_path;

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity BusinessException(Exception e) {
        log.info("Business Exception Handler");
        e.printStackTrace();
        return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR, e.getLocalizedMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(SQLException.class)
    public ResponseEntity SqlException(Exception e) {
        e.printStackTrace();
        log.info("SQL Exception Handler");
        return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR, e.getLocalizedMessage()), HttpStatus.OK);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity NullPointerException(Exception e) {
        e.printStackTrace();
        log.info("NullPointer Exception Handler");
        return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR, e.getLocalizedMessage()), HttpStatus.OK);
    }

    @Setter
    @Getter
    @Data
    class BoardRequest {
        private int user_no;
        private int board_no;
    }

    /**
     * 주석 생성 날짜 - 2021-07-29, 목, 14:21
     * 코드 설명 : 게시글 정보를 받아오는 URL
     * 특이 사항 : X
     * 파일 업로드 여부 : X
     **/
    @RequestMapping(value = "/api/board", method = RequestMethod.POST) // CHECK
    public ResponseEntity GetBoard(@RequestBody String body) {
        BoardRequest boardRequest = new Gson().fromJson(body, BoardRequest.class);
        return boardService.GetBoard(boardRequest.getUser_no(), boardRequest.getBoard_no(), -1);
    }

    /**
     * 주석 생성 날짜 - 2021-07-29, 목, 14:22
     * 코드 설명 : 게시글의 댓글을 불러오는 URL
     * 특이 사항 : 10개씩 리로딩
     * 파일 업로드 여부 : X
     **/
    @RequestMapping(value = "/api/board/comments/{last_index}", method = RequestMethod.POST) //CHECK
    public ResponseEntity GetBoardComments(@RequestBody String body, @PathVariable("last_index") int last_index) {
        BoardRequest boardRequest = new Gson().fromJson(body, BoardRequest.class);
        return boardService.GetBoard(boardRequest.getUser_no(), boardRequest.getBoard_no(), last_index);
    }

    /**
     * 주석 생성 날짜 - 2021-07-29, 목, 14:22
     * 코드 설명 : 게시글을 업로드하는 URL
     * 특이 사항 : 스마트 에디터 내의 임시 사진 URL 을 지정 URL로 변경, 썸네일은 개별 업로드
     * 파일 업로드 여부 : thumbnail
     **/
    @RequestMapping(value = "/api/board/upload", method = RequestMethod.POST) //CHECK
    public ResponseEntity UploadBoard(@RequestParam(value = "board") String body) {
        try {
            Board board = new Gson().fromJson(body, Board.class);

            String content = board.getContent();

            String board_info = board.getArtist_no() + "/";

            String revisedContent = moveBoardsFile(content, board_info);

            String thumbnailURL = extractThumbnail(revisedContent);

            board.setContent(revisedContent);
            board.setThumbnail(thumbnailURL);

            Message message = new Message();

            Artist artist = artistService.getArtistByArtistNo(board.getArtist_no());
            board.setArtist_name(artist.getArtist_name());
            board.setArtist_profile_img(artist.getArtist_profile_img());
            String d = Time.TimeFormatHMS();
            board.setReg_date(d);
            board.setRevise_date(d);
            boardService.insertBoard(board);
            board.setUser_no(artist.getUser_no());
            message.put("board", board);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.UPLOAD_BOARD_SUCCESS, message.getHashMap("UploadBoard()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }

    /**
     * 주석 생성 날짜 - 2021-07-29, 목, 14:23
     * 코드 설명 :
     * 특이 사항 :
     * 파일 업로드 여부 :
     **/
    @RequestMapping(value = "/api/board/edit", method = RequestMethod.POST) //CHECK
    public ResponseEntity EditBoard(@RequestParam("board") String body)  {
        Board board = new Gson().fromJson(body, Board.class);

        String board_content = board.getContent();
        String board_info = board.getArtist_no() + "/";
        String revised_content = moveBoardsFile(board_content, board_info);
        String thumbnailURL = extractThumbnail(revised_content);
        board.setContent(revised_content);
        board.setThumbnail(thumbnailURL);
        return boardService.updateBoard(board);
    }

    @RequestMapping(value = "/api/board/delete/{board_no}", method = RequestMethod.POST) //CHECK
    public ResponseEntity DeleteBoard(@PathVariable("board_no") int board_no) {
        return boardService.deleteBoard(board_no);
    }

    @RequestMapping(value = "/api/board/like", method = RequestMethod.POST) //CHECK
    public ResponseEntity PressPortfolioLike(@RequestBody String body) {
        BoardRequest boardRequest = new Gson().fromJson(body, BoardRequest.class);
        int user_no = boardRequest.getUser_no();
        int board_no = boardRequest.getBoard_no();
        return boardService.updateBoardByLike(board_no, user_no);
    }

    @RequestMapping(value = "/api/board/comment", method = RequestMethod.POST) // CHECK
    public ResponseEntity InsertBoardComment(@RequestBody String body) {
        BoardComment boardComment = new Gson().fromJson(body, BoardComment.class);
        return boardService.updateBoardByComment(boardComment, "UPDATE");
    }

    @Getter
    @Setter
    @Data
    class CommentDeleteRequest {
        private int comment_no;
        private int board_no;
    }

    @RequestMapping(value = "/api/board/comment/delete", method = RequestMethod.POST) //CHECK
    public ResponseEntity DeleteBoardComment(@RequestBody String body) {
        CommentDeleteRequest commentDeleteRequest = new Gson().fromJson(body, CommentDeleteRequest.class);
        BoardComment boardComment = new BoardComment();
        boardComment.setComment_no(commentDeleteRequest.getComment_no());
        boardComment.setBoard_no(commentDeleteRequest.getBoard_no());
        return boardService.updateBoardByComment(boardComment, "DELETE");
    }

    @Getter
    @Setter
    @Data
    class BoardEditRequest {
        private int artist_no;
        private int board_no;
    }

    @RequestMapping(value = "/api/board/edit_source", method = RequestMethod.GET) //CHECK
    public ResponseEntity GetBoardForEdit(@RequestBody String body) {
        BoardEditRequest boardEditRequest = new Gson().fromJson(body, BoardEditRequest.class);
        return boardService.getBoardByBoardNo(boardEditRequest.getBoard_no(), boardEditRequest.getArtist_no());
    }

    @RequestMapping(value = "/api/boardList/{sort}/{last_index}", method = RequestMethod.GET) //CHECK
    public ResponseEntity GetBoardList(@PathVariable("last_index") int last_index, @PathVariable("sort") String sort) {
        return boardService.getBoardList(sort, last_index);
    }

    /*업로드된 파일을 저장하는 함수*/
    private String uploadFile(String originalName, MultipartFile mfile, String board_info) throws IOException {
        UUID uid = UUID.randomUUID();
        originalName = originalName.replace(" ", "");
        String savedName = uid.toString().substring(0, 8) + "_" + originalName;
        FileConverter fileConverter = new FileConverter();
        File file = fileConverter.convert(mfile, uid.toString().substring(0, 8) + "test" + originalName.substring(originalName.lastIndexOf(".")).toLowerCase());
        CDNService cdnService = new CDNService();
        cdnService.upload("api/images/board/thumbnail/" + board_info + savedName, file);
        Files.deleteIfExists(file.toPath());
        return savedName;
    }

    public String moveBoardsFile(String content, String board_info) {
        try {
            CDNService cdnService = new CDNService();
            List<String> url = new ArrayList<>();
            String original_content = content;
            Pattern p = Pattern.compile("src=\"(.*?)\"");
            Matcher m = p.matcher(original_content);
            while (m.find()) {
                log.info("Url : " + m.group(1));
                url.add(m.group(1));
            }
            List<File> files = new ArrayList<>();
            List<String> newUrl = new ArrayList<>();
            if (url.size() > 0) {
                for (String str : url) {
                    String path = "";
                    if (str.contains("vodappserver/"))
                        path = str.substring(str.indexOf("vodappserver/") + 13);
                    else
                        path = str.substring(str.indexOf(".com/") + 5);
                    String filename = path.substring(path.lastIndexOf("/") + 1);
                    log.info("Check Path Before downloading : " + path);
                    files.add(cdnService.download(upload_path, filename, path));
                    cdnService.delete(path);
                }
                if (files.size() > 0) {
                    for (File file : files) {
                        UUID uid = UUID.randomUUID();
                        String originalName = file.getName().replace(" ", "");
                        String savedName = uid.toString().substring(0, 8) + "_" + originalName;
                        cdnService.upload("api/images/board/" + board_info + savedName, file);
                        newUrl.add(cdn_path + "images/board/" + board_info + savedName);
                        file.deleteOnExit();
                    }
                    for (int i = 0; i < url.size(); i++) {
                        original_content = original_content.replace(url.get(i), newUrl.get(i));
                    }
                }
            }

            return original_content;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(e);
        }
    }

    private String extractThumbnail(String content) {
        Pattern p = Pattern.compile("src=\"(.*?)\"");
        Matcher m = p.matcher(content);
        if (m.find()) {
            log.info("Thumbnail URL : " + m.group(1));
            return m.group(1);
        } else {
            log.info("No Image in content");
            return null;
        }
    }


    /** ALL CHECKED **/
}
