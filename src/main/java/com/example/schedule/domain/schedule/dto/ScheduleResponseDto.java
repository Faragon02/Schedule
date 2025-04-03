package com.example.schedule.domain.schedule.dto;

import com.example.schedule.domain.schedule.entity.Schedule;
import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private Long Id;
    private String writer;
    private String title;
    private String contents;
    private String createdAt;
    private String modifiedAt;

    public ScheduleResponseDto(Long id, String title, String contents, String writer) {
        this.Id = id;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
    }

    public ScheduleResponseDto(Schedule schedule) {
        this.Id = schedule.getId();
        this.title = schedule.getTitle();
        this.contents = schedule.getContents();
        this.writer = schedule.getMember().getUserName();
        this.createdAt =  schedule.getCreatedAt().toString();
        this.modifiedAt = schedule.getModifiedAt().toString();
    }
    public static ScheduleResponseDto todo(Schedule board){
        return new ScheduleResponseDto(board);
    }
}
