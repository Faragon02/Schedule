package com.example.schedule.members.controller;

import com.example.schedule.members.dto.LoginRequestDto;
import com.example.schedule.members.dto.LoginResponseDto;
import com.example.schedule.members.service.MemberService;
import com.example.schedule.members.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class  UserController {

    private UserService userService;

    @PostMapping("/login")
    public String login(
            @Valid @ModelAttribute LoginRequestDto requestDto,
            HttpServletResponse response
            ){

        LoginResponseDto responseDto = userService.Login(requestDto.getUserName(), requestDto.getPassword());
        if(responseDto.getId() == null){
            return "login Failed!";
        }

        Cookie cookie = new Cookie("userId", String.valueOf(responseDto.getId()));
        response.addCookie(cookie);

        return "Login 성공!";
    }
    @PostMapping("/Logout")
    public String Logout(
            HttpServletResponse response
    ){
        Cookie cookie = new Cookie("userId", null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "Logout 성공!";
    }
}
