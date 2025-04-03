package com.example.schedule.domain.members.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class UpdatePasswordRequestDto {

    @NotBlank(message = "패스워드를 입력하세요")
    @Size(min= 8 , max = 20, message = "8~ 20자 사이 입력해주세요.")
    private final String oldPassword;

    @NotBlank(message = "패스워드를 입력하세요")
    @Size(min= 8 , max = 20, message = "8~ 20자 사이 입력해주세요.")
    private final String newPassword;

    public UpdatePasswordRequestDto(String oldPassword, String newPassword){
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }
}
