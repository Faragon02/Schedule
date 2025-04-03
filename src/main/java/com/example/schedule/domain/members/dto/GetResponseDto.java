package com.example.schedule.domain.members.dto;

import com.example.schedule.domain.members.entity.Member;
import lombok.Getter;

@Getter
public class GetResponseDto {

    private final  Long id;

    private final String userName;

    private final String email;

    private final String createdAt;

    private final String modifiedAt;

    public GetResponseDto(Member member) {
        this.id = member.getId();
        this.userName = member.getUserName();
        this.email = member.getEmail();
        this.createdAt = member.getCreatedAt().toString();
        this.modifiedAt = member.getModifiedAt().toString();
    }
}
