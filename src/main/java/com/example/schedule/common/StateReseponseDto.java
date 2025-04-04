package com.example.schedule.common;

import lombok.Getter;

@Getter
public class StateReseponseDto {
    private String Message;


    public StateReseponseDto(String message) {
        Message = message;
    }
}
