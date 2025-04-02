package com.example.schedule.board.dto;

import com.example.schedule.board.entity.Board;
import jakarta.persistence.Table;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.logging.SimpleFormatter;

@Getter
public class BoardResponseDto {
    private Long Id;
    private String writer;
    private String title;
    private String contents;
    private String createdAt;
    private String modifiedAt;

    public BoardResponseDto(Long id, String title, String contents,String writer) {
        this.Id = id;
        this.title = title;
        this.contents = contents;
        this.writer = writer;
    }

    public BoardResponseDto(Board board) {
        this.Id = board.getId();
        this.title = board.getTitle();
        this.contents = board.getContents();
        this.writer = board.getWriter();
        this.createdAt =  board.getCreatedAt().toString();
        this.modifiedAt = board.getModifiedAt().toString();
    }
    public static  BoardResponseDto todo(Board board){
        return new BoardResponseDto(board);
    }
}
