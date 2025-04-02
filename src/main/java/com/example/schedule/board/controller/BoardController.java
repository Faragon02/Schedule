package com.example.schedule.board.controller;

import com.example.schedule.board.dto.BoardRequestDto;
import com.example.schedule.board.dto.BoardResponseDto;
import com.example.schedule.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor 
public class BoardController {

    // Cannot invoke "com.example.schedule.board.service.BoardService.scheduleSaveService(String, String, String)" because "this.boardService" is null
    //생성자 생성 실패
    // @RequiredArgsConstructor 사용 안해서? No.
    // final 안붙힘 -> 성공.
    private final BoardService boardService;
    
    
    /*
    * 2025 /04 /01
    * 일정 추가
    * */
    @PostMapping
    public ResponseEntity<BoardResponseDto> scheduleSave(@RequestBody BoardRequestDto dto){
        BoardResponseDto boardResponseDto = boardService.scheduleSaveService(
                dto.getTitle(),
                dto.getContents(),
                dto.getWriter()
        );
       return new ResponseEntity<>(boardResponseDto, HttpStatus.CREATED);
   }
    /*
     * 2025 /04 /01
     * 일정 조회
     * */
   @GetMapping("/{id}")
    public ResponseEntity<BoardResponseDto> findById(@PathVariable Long id){

       BoardResponseDto boardResponseDto =  boardService. scheduleFindByIdService(id);
       return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
   }

    /*
     * 2025 /04 /01
     * 일정 수정
     * */
    @PatchMapping("/{id}")
    public  ResponseEntity<BoardResponseDto> scheduleModified(
            @PathVariable Long id,
            @RequestBody BoardRequestDto dto){
            BoardResponseDto boardResponseDto = boardService.scheduleUpdateService(id, dto.getContents());
        return new ResponseEntity<>(boardResponseDto, HttpStatus.OK);
    }
    /*
     * 2025 /04 /01
     * 일정 삭제
     * */
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(@PathVariable Long id){
        boardService.scheduleDeleteService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
