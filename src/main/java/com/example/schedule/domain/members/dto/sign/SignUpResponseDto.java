package com.example.schedule.domain.members.dto.sign;

import com.example.schedule.domain.schedule.dto.ScheduleResponseDto;
import com.example.schedule.domain.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class SignUpResponseDto {
    private final Long id;

    private final String userName;

    public SignUpResponseDto(Long id, String userName) {
        this.id = id;
        this.userName = userName;
    }
}
