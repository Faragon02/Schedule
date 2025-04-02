package com.example.schedule.members.dto.sign;

import lombok.Getter;

@Getter
public class SignUpResponseDto {
    private final Long id;

    private final String username;

    public SignUpResponseDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }
}
