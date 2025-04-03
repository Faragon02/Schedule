package com.example.schedule.domain.members.controller;

import com.example.schedule.common.Const;
import com.example.schedule.domain.members.dto.GetResponseDto;
import com.example.schedule.domain.members.dto.login.LoginRequestDto;
import com.example.schedule.domain.members.dto.login.LoginResponseDto;
import com.example.schedule.domain.members.dto.sign.SignUpRequestDto;
import com.example.schedule.domain.members.dto.sign.SignUpResponseDto;
import com.example.schedule.domain.members.dto.UpdatePasswordRequestDto;
import com.example.schedule.domain.members.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    /*
     * 2005.04.03
     * Login 기능
     *  */
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(
            @Valid @RequestBody LoginRequestDto dto,
            HttpServletRequest request
    ){

        LoginResponseDto responseDto = memberService.login(dto.getEmail(), dto.getPassword());
        if(responseDto.getId() ==null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error");
        }
        //세션
        HttpSession session = request.getSession();

        // session.setAttribute(Const.LOGIN_USER, String.valueOf(responseDto));

        session.setAttribute(Const.LOGIN_USER, responseDto);
        return ResponseEntity.ok(new LoginResponseDto(responseDto.getId() ));
    }

    /*
     * 2005.04.03
     * LogOut 기능
     *  */
    @PostMapping("/logout")
    public ResponseEntity<String> logOut(
            HttpServletRequest request
    ){
        // 로그인하지 않으면 HttpSession이 null로 반환된다.
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();// 해당 세션(데이터)을 삭제한다.
        }
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    /*
     * 2005.04.03
     * 유저 추가
     *  */
    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> memberSignUp( @RequestBody @Valid SignUpRequestDto dto){

        SignUpResponseDto signUpResponseDto =
                memberService.memberSignUpService(
                dto.getUserName(),
                dto.getPassword(),
                dto.getEmail()
        );
        return new ResponseEntity<>(signUpResponseDto,HttpStatus.OK);
    }

    /*
     * 2005.04.03
     * 유저 조회
     *  */
    @GetMapping
    public ResponseEntity<GetResponseDto> memberFindById(HttpSession session){

        Long userId = memberService.getUserId( (LoginResponseDto) session.getAttribute(Const.LOGIN_USER));
        return new ResponseEntity<>(memberService.memberFindByIdService(userId), HttpStatus.OK);
    }
    /*
     * 2005.04.03
     * 유저 비밀번호 변경
     *  */
    @PatchMapping
    public ResponseEntity<Void> memberUpdate(
            HttpSession session,
            @RequestBody UpdatePasswordRequestDto dto){

//        String c  = (String)session.getAttribute(Const.LOGIN_USER);
//        System.out.println(c);
        Long userId = memberService.getUserId( (LoginResponseDto) session.getAttribute(Const.LOGIN_USER));
        memberService.updatePassword(userId , dto.getOldPassword(), dto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
     * 2005.04.03
     * 유저 정보 삭제
     *  */
    @DeleteMapping
    public ResponseEntity<Void> memberDelete( HttpSession session,
                                              @RequestParam String password){

        Long userId = memberService.getUserId( (LoginResponseDto) session.getAttribute(Const.LOGIN_USER));
         memberService.memberDeleteService(userId, password );
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
