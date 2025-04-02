package com.example.schedule.members.service;

import com.example.schedule.members.dto.LoginResponseDto;
import com.example.schedule.members.entity.Member;
import com.example.schedule.members.repository.MemberRepository;
import com.example.schedule.members.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    /*
     *2025 /04 /02
     * 로그인 기능.
     * */
    public LoginResponseDto Login(String username, String password){
          Member member = userRepository.findMemberByLoginOrElseTrow(username, password);
          return new LoginResponseDto(member.getId());
    }
}
