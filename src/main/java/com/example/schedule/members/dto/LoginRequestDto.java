package com.example.schedule.members.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.antlr.v4.runtime.misc.NotNull;

//implementation 'org.springframework.boot:spring-boot-starter-validation' 설치 할것.

@Getter
@AllArgsConstructor
public class LoginRequestDto {

    //입력한 아이디
    @NotBlank
    private final String userName;

    //입력한 패스워드
    @NotBlank
    private final String password;
}
