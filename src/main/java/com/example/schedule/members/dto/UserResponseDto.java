package com.example.schedule.members.dto;

import lombok.Getter;

@Getter
public class UserResponseDto {
    private final Long id;
    private final String name;


    public UserResponseDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
