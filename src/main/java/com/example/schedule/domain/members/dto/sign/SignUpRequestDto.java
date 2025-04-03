package com.example.schedule.domain.members.dto.sign;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class SignUpRequestDto {

    @NotBlank(message = "이름을 입력하세요")
    private String userName;

    @NotBlank(message = "패스워드를 입력하세요")
    @Min(value = 8, message = "8~ 20자 사이 입력해주세요.")
    @Max(value = 20, message = "8~ 20자 사이 입력해주세요.")
    private String password;

    @NotBlank(message = "이메일에 공백이 포함될 수 없습니다.")
    @Pattern(regexp="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+[.][a-zA-Z]{2,3}$", message="이메일 주소 양식을 확인해주세요")
    private String email;

    public SignUpRequestDto(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
}
