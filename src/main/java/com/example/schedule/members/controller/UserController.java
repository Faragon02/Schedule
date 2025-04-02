package com.example.schedule.members.controller;

import com.example.common.Const;
import com.example.schedule.members.dto.login.LoginRequestDto;
import com.example.schedule.members.dto.login.LoginResponseDto;
import com.example.schedule.members.service.UserService;
import jakarta.servlet.http.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class  UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> Login(
            @Valid @RequestBody  LoginRequestDto dto,
            HttpServletRequest request
    ){

        LoginResponseDto responseDto = userService.login(dto.getUserName(), dto.getPassword());
        if(responseDto.getId() ==null){

            System.out.println("is null");
             throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Error");
        }
        Cookie cookie = new Cookie("userId", String.valueOf(responseDto.getId()));
        HttpSession session = request.getSession();
        session.setAttribute(Const.LOGIN_USER,  cookie);
        if(session != null) {
            session.invalidate(); // 해당 세션(데이터)을 삭제한다.
        }
         return ResponseEntity.ok(new LoginResponseDto(responseDto.getId() ));
    }
}
