package com.example.schedule.members.dto.sign;

import lombok.Getter;

@Getter
public class SignUpRequestDto {
    private final String userName;

    private final String password;

    private final String email;

    public SignUpRequestDto(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
}
