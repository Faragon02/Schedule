package com.example.schedule.domain.members.dto.sign;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    @NotBlank(message = "아이디를 입력하세요")
    private String userName;

    @NotBlank(message = "패스워드를 입력하세요")
    private String password;

    @NotBlank(message = "이메일을 입력하세요")
    private String email;

    public SignUpRequestDto(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
}
