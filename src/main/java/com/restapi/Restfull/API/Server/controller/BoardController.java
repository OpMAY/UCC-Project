package com.restapi.Restfull.API.Server.controller;

import com.google.gson.Gson;
import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.Artist;
import com.restapi.Restfull.API.Server.models.Board;
import com.restapi.Restfull.API.Server.models.BoardComment;
import com.restapi.Restfull.API.Server.models.Upload;
import com.restapi.Restfull.API.Server.response.DefaultRes;
import com.restapi.Restfull.API.Server.response.Message;
import com.restapi.Restfull.API.Server.response.ResMessage;
import com.restapi.Restfull.API.Server.response.StatusCode;
import com.restapi.Restfull.API.Server.services.ArtistService;
import com.restapi.Restfull.API.Server.services.BoardService;
import com.restapi.Restfull.API.Server.services.CDNService;
import com.restapi.Restfull.API.Server.utility.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
        주석 생성 날짜 - 2021-07-29, 목, 14:21
        코드 설명 : 게시글 정보를 받아오는 URL
        특이 사항 : X
        파일 업로드 여부 : X
    **/
    @RequestMapping(value = "/api/board", method = RequestMethod.POST) // CHECK
    public ResponseEntity GetBoard(@RequestBody String body) {
        BoardRequest boardRequest = new Gson().fromJson(body, BoardRequest.class);
        return boardService.GetBoard(boardRequest.getUser_no(), boardRequest.getBoard_no(), -1);
    }

    /** 
        주석 생성 날짜 - 2021-07-29, 목, 14:22
        코드 설명 : 게시글의 댓글을 불러오는 URL
        특이 사항 : 10개씩 리로딩
        파일 업로드 여부 : X
    **/
    @RequestMapping(value = "/api/board/comments/{last_index}", method = RequestMethod.POST) //CHECK
    public ResponseEntity GetBoardComments(@RequestBody String body, @PathVariable("last_index") int last_index) {
        BoardRequest boardRequest = new Gson().fromJson(body, BoardRequest.class);
        log.info(last_index);
        return boardService.GetBoard(boardRequest.getUser_no(), boardRequest.getBoard_no(), last_index);
    }

    /** 
        주석 생성 날짜 - 2021-07-29, 목, 14:22
        코드 설명 : 게시글을 업로드하는 URL
        특이 사항 : 스마트 에디터 내의 임시 사진 URL 을 지정 URL로 변경, 썸네일은 개별 업로드
        파일 업로드 여부 : thumbnail
    **/
    @RequestMapping(value = "/api/board/upload", method = RequestMethod.POST) //CHECK
    public ResponseEntity UploadBoard(@RequestParam(value = "board") String body,
                                      @RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail) {
        try {

            Board board = new Gson().fromJson(body, Board.class);
            StringBuilder board_info = new StringBuilder();
            board_info.append(board.getArtist_no());
            board_info.append("/");

            log.info(thumbnail);

            String content = board.getContent();

            String revisedContent = moveBoardsFile(content, board_info.toString());

            board.setContent(revisedContent);

            Message message = new Message();
            if (thumbnail != null) {
                if (!thumbnail.isEmpty()) {
                    if (!Format.CheckIMGFile(thumbnail.getOriginalFilename())) {
                        return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_UNSUPPORTED), HttpStatus.OK);
                    }
                    /** File Upload Log Logic*/
                    log.info("originalName:" + thumbnail.getOriginalFilename());
                    log.info("size:" + thumbnail.getSize());
                    log.info("ContentType:" + thumbnail.getContentType());


                    /** File Upload Logic */
                    String file_name = uploadFile(thumbnail.getOriginalFilename(), thumbnail, board_info.toString());

                    URLConverter urlConverter = new URLConverter();
                    /** Board Set **/
                    board.setThumbnail(urlConverter.convertSpecialLetter(cdn_path + "images/board/thumbnail/" + board_info.toString() + file_name));

                    /** Response Json Logic*/
                    message.put("files", new Upload(file_name, urlConverter.convertSpecialLetter(cdn_path + "images/board/thumbnail/" + board_info.toString() + file_name)));
                }
            }

            Artist artist = artistService.getArtistByArtistNo(board.getArtist_no());
            board.setArtist_name(artist.getArtist_name());
            board.setArtist_profile_img(artist.getArtist_profile_img());
            String d = Time.TimeFormatHMS();
            board.setReg_date(d);
            board.setRevise_date(d);
            boardService.insertBoard(board);


            Board board1 = boardService.getBoard(board.getBoard_no());
            board1.setUser_no(artist.getUser_no());
            message.put("board", board1);

            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.UPLOAD_BOARD_SUCCESS, message.getHashMap("UploadBoard()")), HttpStatus.OK);
        } catch (
                JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        } catch (
                IOException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /** 
        주석 생성 날짜 - 2021-07-29, 목, 14:23
        코드 설명 : 
        특이 사항 : 
        파일 업로드 여부 : 
    **/
    @RequestMapping(value = "/api/board/edit", method = RequestMethod.POST) //CHECK
    public ResponseEntity EditBoard(@RequestParam("board") String body,
                                    @RequestParam(value = "thumbnail", required = false) MultipartFile thumbnail) throws IOException {
        Board board = new Gson().fromJson(body, Board.class);
        Message message = new Message();
        StringBuilder board_info = new StringBuilder();
        board_info.append(board.getArtist_no());
        board_info.append("/");

        String board_content = board.getContent();
        String revised_content = moveBoardsFile(board_content, board_info.toString());
        board.setContent(revised_content);

        if (thumbnail != null) {
            if (!thumbnail.isEmpty()) {
                if (!Format.CheckIMGFile(thumbnail.getOriginalFilename())) {
                    return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_UNSUPPORTED), HttpStatus.OK);
                }
                /** File Upload Log Logic*/
                log.info("originalName:" + thumbnail.getOriginalFilename());
                log.info("size:" + thumbnail.getSize());
                log.info("ContentType:" + thumbnail.getContentType());

                /** File Upload Logic */
                String file_name = uploadFile(thumbnail.getOriginalFilename(), thumbnail, board_info.toString());

                /** Board Set **/
                URLConverter urlConverter = new URLConverter();
                board.setThumbnail(urlConverter.convertSpecialLetter(cdn_path + "images/board/thumbnail/" + board_info.toString() + file_name));

                /** Response Json Logic*/
                message.put("name", file_name);
                message.put("url", urlConverter.convertSpecialLetter(cdn_path + "images/board/thumbnail/" + board_info.toString() + file_name));
            }
        }
        return boardService.updateBoard(board, message);
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
        log.info(body);
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
        File file = fileConverter.convert(mfile);
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
            int index;
            while (true) {
                index = content.indexOf("<img src=");
                if (index < 0)
                    break;
                int next_multi_index = content.substring(index).indexOf(">");
                String substring = content.substring(index + 10, index + next_multi_index - 1);
                if(substring.contains("/api/temp/")) {
                    url.add(substring);
                }
                content = content.substring(index).substring(next_multi_index + 1);
            }
            List<File> files = new ArrayList<>();
            List<String> newUrl = new ArrayList<>();
            if(url.size() > 0) {
                for (String str : url) {
                    String path = str.substring(str.indexOf("vodappserver/") + 13);
                    String filename = path.substring(path.lastIndexOf("/") + 1);
                    log.info("Check Path Before downloading : " + path);
                    files.add(cdnService.download(upload_path, filename, path));
                    cdnService.delete(path);
                }
                if(files.size() > 0) {
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


    /** ALL CHECKED **/
}
