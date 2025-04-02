package com.example.schedule.board.dto;

import jakarta.persistence.Table;
import lombok.Getter;

@Getter
public class BoardRequestDto {

    private String title;
    private String contents;
    private String writer;

    public BoardRequestDto( String title, String contents, String writer) {

        this.title = title;
        this.contents = contents;
        this.writer = writer;
    }
}
