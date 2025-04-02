package com.example.schedule.board.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Entity
@Table(name = "schedule")
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(nullable = false)
    private  String title;

    @Column(columnDefinition = "longtext", nullable = false)
    private  String contents;

    @Column(nullable = false)
    private  String writer;

    //빈 객체가 생성해야하는 이유???
    public Board() {

    }


    public Board( String title, String contents, String writer) {
        this.title = title;
        this.contents = contents;
        this.writer = writer;

    }

    public void update(String contents){
        this.contents = contents;
    }
}
