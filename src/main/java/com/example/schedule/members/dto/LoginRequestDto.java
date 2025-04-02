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
    @NotBlank(message = "아이디에 공백이 포함될 수 없습니다.")
    private final String userName;

    //입력한 패스워드
    @NotBlank(message = "비밀 번호에 공백이 포함될 수 없습니다.")
    private final String password;
}
