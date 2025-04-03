package com.example.schedule.domain.members.service;

import com.example.schedule.domain.members.dto.GetResponseDto;
import com.example.schedule.domain.members.dto.login.LoginResponseDto;
import com.example.schedule.domain.members.dto.sign.SignUpResponseDto;
import com.example.schedule.domain.members.entity.Member;
import com.example.schedule.domain.members.repository.MemberRepository;
import com.example.schedule.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
     /*
     *2025 /04 /02
     * 로그인 기능.
     * */
    public LoginResponseDto login(String userName, String password){

        Member member = memberRepository.findByLoginOrElseTrow(userName, password);
        if(member.getId() == null){
            throw new CustomException("로그인 실패");
        }
        return new LoginResponseDto(member.getId());
    }

    /*
     * 2025 /04 /01
     *유저 추가 서비스
     * */
    public SignUpResponseDto memberSignUpService( String userName, String password, String email){

        Member member = new Member(userName, password, email);
        Member saveMember =memberRepository.save(member);
        return new SignUpResponseDto(saveMember.getId(), saveMember.getUserName());
    }

    /*
     * 2025 /04 /01
     *유저 조회 서비스
     * */
    public GetResponseDto memberFindByIdService(Long id){

        Member findMember = memberRepository.findByIdOrElseThrow(id);
        if(findMember == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "아이디 또는 비밀번호를 잘못 입력 했습니다.");
        }

        return new GetResponseDto(findMember);
    }

    /*
     * 2025 /04 /01
     * 유저 패스워드 수정 서비스
     * */
    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword){
        Member findMember = memberRepository.findByIdOrElseThrow(id);

        if(!findMember.getPassword().equals(oldPassword)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "기존 비밀 번호와 다릅니다.");
        }
        findMember.updatePassword(newPassword);
    }

    public void memberDeleteService(Long id){
        Member findMember = memberRepository.findByIdOrElseThrow(id);
        if(findMember == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "삭제할 유저 정보가 존재하지 않습니다.");
        }
        memberRepository.delete(findMember);
    }



}
