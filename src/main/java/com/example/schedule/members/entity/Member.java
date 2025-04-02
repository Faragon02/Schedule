package com.example.schedule.members.entity;

import com.example.schedule.board.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "member")
public class Member extends BaseEntity {
    //식별자
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //이메일
    @Column(nullable = false, unique = true)
    private String email;

    //유저 아이디
    @Column(nullable = false)
    private String userName;

    //유저 패스워드
    @Column(nullable = false)
    private String password;

    public  Member(){
    }

    //가입
    public Member( String userName, String password, String email){
        this.userName = userName;
        this.password = password;
        this.email = email;
    }
    //패워드 변경
    public void updatePassword(String password) {
        this.password = password;
    }
    //Login 전용
    public Member(Long id, String userName, String password) {
        this.Id = id;
        this.userName = userName;
        this.password = password;
    }
}
