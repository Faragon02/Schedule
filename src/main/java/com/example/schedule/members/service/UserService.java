package com.example.schedule.members.service;

import com.example.schedule.members.dto.LoginResponseDto;
import com.example.schedule.members.entity.Member;
import com.example.schedule.members.repository.MemberRepository;
import com.example.schedule.members.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    /*
     *2025 /04 /02
     * 로그인 기능.
     * */
    public LoginResponseDto login(String username, String password){

          Member member = userRepository.findByLoginOrElseTrow(username, password);
          if(member.getId() == null){
              log.warn("user not fonud");
          }
          return new LoginResponseDto(member.getId());
    }
}
