package com.example.schedule.members.service;

import com.example.schedule.members.dto.GetResponseDto;
import com.example.schedule.members.dto.SignUpRequestDto;
import com.example.schedule.members.dto.SignUpResponseDto;
import com.example.schedule.members.entity.Member;
import com.example.schedule.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public SignUpResponseDto memberSignUpService(String username, String password, String email){
        Member member = new Member(username, password, email);
        Member saveMember =memberRepository.save(member);
        return new SignUpResponseDto(saveMember.getId(), saveMember.getUsername());
    }


    public GetResponseDto memberFindByIdService(Long id){

        Member findMember = memberRepository.findByIdOrElseThrow(id);

        return new GetResponseDto(findMember);
    }

    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword){
        Member findMember = memberRepository.findByIdOrElseThrow(id);

        if(!findMember.getPassword().equals(oldPassword)){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }
        findMember.updatePassword(newPassword);
    }

}
