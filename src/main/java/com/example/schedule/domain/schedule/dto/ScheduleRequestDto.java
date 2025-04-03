package com.example.schedule.domain.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class ScheduleRequestDto {

    @NotBlank(message = "제목을 입력하세요")
    private String title;

    @NotBlank(message = "내용을 입력하세요")
    private String contents;

    private String writer;

    public ScheduleRequestDto(String title, String contents, String writer) {

        this.title = title;
        this.contents = contents;
        this.writer = writer;
    }
}
