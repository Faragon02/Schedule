package com.example.schedule.domain.members.dto.login;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;

//implementation 'org.springframework.boot:spring-boot-starter-validation' 설치 할것.

@Getter
@AllArgsConstructor
public class LoginRequestDto {

    //입력한 이메일
    @NotBlank(message = "이메일에 공백이 포함될 수 없습니다.")
    @Pattern(regexp="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])+[.][a-zA-Z]{2,3}$", message="이메일 주소 양식을 확인해주세요")
    private final String email;

    //입력한 패스워드
    @NotBlank(message = "비밀 번호에 공백이 포함될 수 없습니다.")
    @Min(value = 8, message = "8~ 20자 사이 입력해주세요.")
    @Max(value = 20, message = "8~ 20자 사이 입력해주세요.")
    private final String password;
}
