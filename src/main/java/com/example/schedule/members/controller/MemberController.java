package com.example.schedule.members.controller;

import com.example.schedule.members.dto.GetResponseDto;
import com.example.schedule.members.dto.SignUpRequestDto;
import com.example.schedule.members.dto.SignUpResponseDto;
import com.example.schedule.members.dto.UpdatePasswordRequestDto;
import com.example.schedule.members.entity.Member;
import com.example.schedule.members.repository.MemberRepository;
import com.example.schedule.members.service.MemberService;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;

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
         memberDelete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
