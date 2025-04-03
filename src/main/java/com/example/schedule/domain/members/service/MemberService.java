package com.example.schedule.domain.members.service;

import com.example.schedule.common.Const;
import com.example.schedule.domain.members.dto.GetResponseDto;
import com.example.schedule.domain.members.dto.login.LoginResponseDto;
import com.example.schedule.domain.members.dto.sign.SignUpResponseDto;
import com.example.schedule.domain.members.entity.Member;
import com.example.schedule.domain.members.repository.MemberRepository;
import com.example.schedule.exception.BadCredentialsException;
import com.example.schedule.exception.NotAuthenticatedException;
import com.example.schedule.exception.PasswordMismatchException;
import com.sun.jdi.request.DuplicateRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;


    /*
     *2025 /04 /04
     * 로그인 확인
     * */
    public  Long getUserId(@SessionAttribute(name = Const.LOGIN_USER, required = false) LoginResponseDto checked){

        if(checked.getId() == null){
            throw new NotAuthenticatedException("로그인 해주세요");
        }
        return checked.getId();
    }

     /*
     *2025 /04 /02
     * 로그인 기능.
     * */

    public LoginResponseDto login(String email, String password){

        Member member = memberRepository.findByLoginOrElseTrow(email, password);
        if(member.getId() == null){
            throw new BadCredentialsException("로그인 실패, 이메일 혹은 비밀번호를 입력하세요.");
        }
        return new LoginResponseDto(member.getId());
    }

    /*
     * 2025 /04 /01
     *유저 추가 서비스
     * */
    public SignUpResponseDto memberSignUpService( String userName, String password, String email){

       Member member = new Member(userName, password, email);

        if(memberRepository.isExist(email)){
            throw new DuplicateKeyException("이미 가입된 정보입니다.");
        }
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
            throw new PasswordMismatchException( "기존 비밀 번호와 다릅니다.");
        }
        findMember.updatePassword(newPassword);
    }

    public void memberDeleteService(Long id, String password){
        Member findMember = memberRepository.findByIdOrElseThrow(id);
        if(!findMember.getPassword().equals(password)){
            throw new BadCredentialsException("입력한 패스워드와 다릅니다.");
        }
        memberRepository.delete(findMember);
    }
}
