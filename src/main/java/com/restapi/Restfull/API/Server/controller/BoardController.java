package com.restapi.Restfull.API.Server.controller;

import com.restapi.Restfull.API.Server.exceptions.BusinessException;
import com.restapi.Restfull.API.Server.models.*;
import com.restapi.Restfull.API.Server.response.*;
import com.restapi.Restfull.API.Server.services.ArtistService;
import com.restapi.Restfull.API.Server.services.BoardService;
import com.restapi.Restfull.API.Server.services.CDNService;
import com.restapi.Restfull.API.Server.utility.Format;
import com.restapi.Restfull.API.Server.utility.Time;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

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
    class BoardRequest{
        private int user_no;
        private int board_no;
    }


    @RequestMapping(value = "/api/board", method = RequestMethod.POST) // CHECK
    public ResponseEntity GetBoard(@ModelAttribute BoardRequest boardRequest){
        return boardService.GetBoard(boardRequest.getUser_no(), boardRequest.getBoard_no(), -1);
    }

    @RequestMapping(value = "/api/board/comments/{start_index}", method = RequestMethod.POST) //CHECK
    public ResponseEntity GetBoardComments(@ModelAttribute BoardRequest boardRequest, @PathVariable("start_index") int start_index){
        return boardService.GetBoard(boardRequest.getUser_no(), boardRequest.getBoard_no(), start_index);
    }

    @RequestMapping(value = "/api/board/upload", method = RequestMethod.POST) //CHECK
    public ResponseEntity UploadBoard(@RequestPart(value = "board")Board board,
                                      @RequestPart(value = "thumbnail", required = false)MultipartFile thumbnail){
        try{
            Message message = new Message();
            if(!thumbnail.isEmpty()){
                if(!Format.CheckIMGFile(thumbnail.getOriginalFilename())){
                    return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_UNSUPPORTED), HttpStatus.OK);
                }
                /** File Upload Log Logic*/
                log.info("originalName:" + thumbnail.getOriginalFilename());
                log.info("size:" + thumbnail.getSize());
                log.info("ContentType:" + thumbnail.getContentType());

                /** File Upload Logic */
                String file_name = uploadFile(thumbnail.getOriginalFilename(), thumbnail.getBytes());

                /** Board Set **/
                board.setThumbnail(cdn_path + file_name);

                /** Response Json Logic*/
                message.put("files", new Upload(file_name, cdn_path + file_name));
            }
            Artist artist = artistService.getArtistByArtistNo(board.getArtist_no());
            board.setArtist_name(artist.getArtist_name());
            board.setArtist_profile_img(artist.getArtist_profile_img());
            String d = Time.TimeFormatHMS();
            board.setReg_date(d);
            board.setRevise_date(d);
            boardService.insertBoard(board);

            Board board1 = boardService.getBoardByBoardNo(board.getBoard_no());
            board1.setUser_no(artist.getUser_no());
            message.put("board", board1);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.UPLOAD_BOARD_SUCCESS, message.getHashMap("UploadBoard()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/board/edit", method = RequestMethod.POST) //CHECK
    public ResponseEntity EditBoard(@RequestPart("board") Board board,
                                    @RequestPart(value = "thumbnail", required = false) MultipartFile thumbnail) throws IOException {
        Message message = new Message();
        if(!thumbnail.isEmpty()){
            if(!Format.CheckIMGFile(thumbnail.getOriginalFilename())){
                return new ResponseEntity(DefaultRes.res(StatusCode.BAD_REQUEST, ResMessage.FILE_TYPE_UNSUPPORTED), HttpStatus.OK);
            }
            /** File Upload Log Logic*/
            log.info("originalName:" + thumbnail.getOriginalFilename());
            log.info("size:" + thumbnail.getSize());
            log.info("ContentType:" + thumbnail.getContentType());

            /** File Upload Logic */
            String file_name = uploadFile(thumbnail.getOriginalFilename(), thumbnail.getBytes());

            /** Board Set **/
            board.setThumbnail(cdn_path + file_name);

            /** Response Json Logic*/
            message.put("name", file_name);
            message.put("url", cdn_path + file_name);
        }
        return boardService.updateBoard(board, message);
    }

    @RequestMapping(value = "/api/board/delete/{board_no}", method = RequestMethod.POST) //CHECK
    public ResponseEntity DeleteBoard(@PathVariable("board_no") int board_no){
        return boardService.deleteBoard(board_no);
    }

    @RequestMapping(value = "/api/board/like", method = RequestMethod.POST) //CHECK
    public ResponseEntity PressPortfolioLike(@ModelAttribute BoardRequest boardRequest){
        int user_no = boardRequest.getUser_no();
        int board_no = boardRequest.getBoard_no();
        return boardService.updateBoardByLike(board_no, user_no);
    }

    @RequestMapping(value = "/api/board/comment", method = RequestMethod.POST) // CHECK
    public ResponseEntity InsertBoardComment(@ModelAttribute BoardComment boardComment){
        return boardService.updateBoardByComment(boardComment, "UPDATE");
    }

    @Getter
    @Setter
    @Data
    class CommentDeleteRequest{
        private int comment_no;
        private int board_no;
    }

    @RequestMapping(value = "/api/board/comment/delete", method = RequestMethod.POST) //CHECK
    public ResponseEntity DeleteBoardComment(@ModelAttribute CommentDeleteRequest commentDeleteRequest){
        BoardComment boardComment = new BoardComment();
        boardComment.setComment_no(commentDeleteRequest.getComment_no());
        boardComment.setBoard_no(commentDeleteRequest.getBoard_no());
        return boardService.updateBoardByComment(boardComment, "DELETE");
    }

    @RequestMapping(value = "/api/board/edit_source/{board_no}", method = RequestMethod.GET) //CHECK
    public ResponseEntity GetBoardForEdit(@PathVariable("board_no") int board_no){
        try {
            Message message = new Message();
            // Board SET
            Board board = boardService.getBoardByBoardNo(board_no);
            // Response Message SET
            message.put("board", board);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_BOARD_SUCCESS, message.getHashMap("GetBoardForEdit()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }

    @RequestMapping(value = "/api/boardList/{sort}/{start_index}", method = RequestMethod.GET) //CHECK
    public ResponseEntity GetBoardList(@PathVariable("start_index") int start_index, @PathVariable("sort") String sort){
        try {
            Message message = new Message();

            List<Board> boardList = boardService.getBoardList(sort, start_index);

            message.put("boards", boardList);
            message.put("sort", sort);
            return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResMessage.GET_BOARD_LIST_SUCCESS, message.getHashMap("GetBoardList()")), HttpStatus.OK);
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseEntity(DefaultRes.res(StatusCode.INTERNAL_SERVER_ERROR, ResMessage.INTERNAL_SERVER_ERROR), HttpStatus.OK);
        }
    }

    /*업로드된 파일을 저장하는 함수*/
    private String uploadFile(String originalName, byte[] fileDate) throws IOException {
        UUID uid = UUID.randomUUID();
        originalName = originalName.replace(" ", "");
        String savedName = uid.toString().substring(0, 8) + "_" + originalName;
        File target = new File(upload_path, savedName);
        //org.springframework.util 패키지의 FileCopyUtils는 파일 데이터를 파일로 처리하거나, 복사하는 등의 기능이 있다.
        FileCopyUtils.copy(fileDate, target);
        CDNService cdnService = new CDNService();
        //cdnService.upload("api/" + savedName, target);
        return savedName;
    }

    /** ALL CHECKED **/
}
