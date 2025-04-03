package com.example.schedule.domain.schedule.controller;

import com.example.schedule.common.Const;
import com.example.schedule.domain.members.dto.login.LoginResponseDto;
import com.example.schedule.domain.members.service.MemberService;
import com.example.schedule.domain.schedule.dto.ScheduleRequestDto;
import com.example.schedule.domain.schedule.dto.ScheduleResponseDto;
import com.example.schedule.domain.schedule.service.SchedulesService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor 
public class ScheduleController {

    // Cannot invoke "com.example.schedule.board.service.BoardService.scheduleSaveService(String, String, String)" because "this.boardService" is null
    //생성자 생성 실패
    // @RequiredArgsConstructor 사용 안해서? No.
    // final 안붙힘 -> 성공.
    private final SchedulesService scheduleSaveService;
    private final MemberService memberService;
    /*
    * 2025 /04 /01
    * 일정 추가
    * */
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> scheduleSave(
            HttpSession session,
            @RequestBody @Valid ScheduleRequestDto dto){

        Long userId = memberService.getUserId( (LoginResponseDto) session.getAttribute(Const.LOGIN_USER));
        ScheduleResponseDto scheduleResponseDto = scheduleSaveService.scheduleSaveService(
                dto.getTitle(),
                dto.getContents(),
                userId
        );
       return new ResponseEntity<>(scheduleResponseDto, HttpStatus.CREATED);
   }
    /*
     * 2025 /04 /01
     * 일정 조회
     * 로그인 정보 확인하고 저장
     * */
   @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findById(
            HttpSession session,
            @PathVariable @NotNull Long id){

       memberService.getUserId( (LoginResponseDto) session.getAttribute(Const.LOGIN_USER));
       ScheduleResponseDto scheduleResponseDto =  scheduleSaveService. scheduleFindByIdService(id);
       return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
   }

    /*
     * 2025 /04 /01
     * 일정 수정
     * */
    @PatchMapping("/{id}")
    public  ResponseEntity<ScheduleResponseDto> scheduleModified(
            HttpSession session,
            @PathVariable @NotNull Long id,
            @RequestBody ScheduleRequestDto dto){
        memberService.getUserId( (LoginResponseDto) session.getAttribute(Const.LOGIN_USER));
        ScheduleResponseDto scheduleResponseDto = scheduleSaveService.scheduleUpdateService(id, dto.getContents());
        return new ResponseEntity<>(scheduleResponseDto, HttpStatus.OK);
    }
    /*
     * 2025 /04 /01
     * 일정 삭제
     * */
    @DeleteMapping("/{id}")
    public  ResponseEntity<Void> delete(
            HttpSession session,
            @PathVariable @NotNull Long id)
    {
        memberService.getUserId( (LoginResponseDto) session.getAttribute(Const.LOGIN_USER));
        scheduleSaveService.scheduleDeleteService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
