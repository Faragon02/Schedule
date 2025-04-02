package com.example.schedule.members.entity;

import com.example.schedule.board.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    //이메일
    @Column(nullable = false)
    private String email;

    //실명
    @Column(nullable = false)
    private String realname;

    //유저 아이디
    @Column(nullable = false, unique = true)
    private String username;

    //유저 패스워드
    @Column(nullable = false)
    private String password;

    public  Member(){
    }

    //가입
    public Member(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    //패워드 변경
    public void updatePassword(String password) {
        this.password = password;
    }
    //Login 전용
    public Member(Long id, String email, String realname, String username, String password) {
        this.Id = id;
        this.email = email;
        this.realname = realname;
        this.username = username;
        this.password = password;
    }
}
