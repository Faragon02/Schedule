package com.example.schedule.members.controller;

import com.example.schedule.members.dto.GetResponseDto;
import com.example.schedule.members.dto.sign.SignUpRequestDto;
import com.example.schedule.members.dto.sign.SignUpResponseDto;
import com.example.schedule.members.dto.UpdatePasswordRequestDto;
import com.example.schedule.members.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    //유저 추가
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> memberSignUp(@RequestBody SignUpRequestDto dto){

        SignUpResponseDto signUpResponseDto =
                memberService.memberSignUpService(
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmail()
        );

        return new ResponseEntity<>(signUpResponseDto, HttpStatus.OK);
    }

    //유저 조회
    @GetMapping("/{id}")
    public ResponseEntity<GetResponseDto> memberFindById(@PathVariable Long id){

        return new ResponseEntity<>(memberService.memberFindByIdService(id), HttpStatus.OK);
    }
    //유저 비밀 번호 변경
    @PatchMapping("/{id}")
    public ResponseEntity<Void> memberUpdate(
            @PathVariable Long id,
            @RequestBody UpdatePasswordRequestDto dto){

            memberService.updatePassword(id, dto.getOldPassword(), dto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    //유저 정보 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> memberDelete(@PathVariable Long id){
         memberService.memberDeleteService(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
