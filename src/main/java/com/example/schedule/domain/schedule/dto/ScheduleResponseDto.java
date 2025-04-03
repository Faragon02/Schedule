package com.example.schedule.domain.schedule.dto;

import com.example.schedule.domain.schedule.entity.Board;
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

    public ScheduleResponseDto(Board board) {
        this.Id = board.getId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.writer = board.getWriter();
        this.createdAt =  board.getCreatedAt().toString();
        this.modifiedAt = board.getModifiedAt().toString();
    }
    public static ScheduleResponseDto todo(Board board){
        return new ScheduleResponseDto(board);
    }
}
