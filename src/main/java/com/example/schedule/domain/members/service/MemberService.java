package com.example.schedule.domain.members.service;

import com.example.schedule.common.Const;
import com.example.schedule.common.StateReseponseDto;
import com.example.schedule.config.PasswordEncoder;
import com.example.schedule.domain.members.dto.GetResponseDto;
import com.example.schedule.domain.members.dto.login.LoginResponseDto;
import com.example.schedule.domain.members.entity.Member;
import com.example.schedule.domain.members.repository.MemberRepository;
import com.example.schedule.exception.BadCredentialsException;
import com.example.schedule.exception.NotAuthenticatedException;
import com.example.schedule.exception.PasswordMismatchException;
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
    private final PasswordEncoder passwordEncoder;

    /*
     *2025 /04 /04
     * 2025/ 04 /04
     * 로그인 확인
     * Schedule 혹은 로그인정보가 필요한 곳에서 기능을 체크한다.
     * */
    public  Long getUserId(@SessionAttribute(name = Const.LOGIN_USER, required = false) LoginResponseDto checked){

        if(checked.getId() == null){
            throw new NotAuthenticatedException("로그인 해주세요");
        }
        return checked.getId();
    }

     /*
     *2025 /04 /02
     * 2025/ 04 /04
     * 로그인 기능.
     * 동일한 이메일과 비밀 번호를 가지 유저를 찾고 확인한다.
     *  실패 시 메세지 우선 순서
     *   1. 요청한 정보를 찾을수 없습니다.
     *   2.로그인 실패, 로그인 실패, 이메일 혹은 비밀번호를 확인하세요.
     * */

    public LoginResponseDto login(String email, String password){

        Member member = memberRepository.findByLoginOrElseTrow(email, password);
        if(!passwordEncoder.matches(password, member.getPassword())){
            System.out.println(passwordEncoder.matches(member.getPassword(), password));
            throw new BadCredentialsException("로그인 실패, 이메일 혹은 비밀번호를 확인하세요.");
        }
        return new LoginResponseDto(member.getId());
    }

    /*
     * 2025 /04 /01
     * 2025/ 04 /04
     *유저 추가 서비스
     *  실패 시 메세지 우선 순서
     *   1. 요청한 정보를 찾을수 없습니다.
     *   2.이미 가입된 정보입니다.
     *
     * */
    public StateReseponseDto memberSignUpService(String userName, String password, String email){

       Member member = new Member(userName,passwordEncoder.encode(password), email);
        //email을 가지는 유저가 있는지 확인(중복 불가)
        if(memberRepository.isExist(email)){
            throw new DuplicateKeyException("이미 가입된 정보입니다.");
        }
        Member saveMember =memberRepository.save(member);
        return new StateReseponseDto("로그인 되었습니다!");
    }

    /*
     * 2025 /04 /01
     * 2025/ 04 /04
     *유저 조회 서비스
     * Session을 통해 UserId를 받아 유저를 조회한다.
     *
     * 실패 시 메세지 우선 순서
     *   1. 요청한 정보를 찾을수 없습니다.
     *   2.아이디 또는 비밀번호를 잘못 입력 했습니다.
     *  3.로그인 해주세요.
     * */
    public GetResponseDto memberFindByIdService(Long id){


        Member findMember = memberRepository.findByIdOrElseThrow(id);
        if(findMember == null){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "존재하지 않는 유저 입니다.");
        }

        return new GetResponseDto(findMember);
    }

    /*
     * 2025 /04 /01
     * 2025/ 04 /04
     * 유저 패스워드 수정 서비스
     * 원래 비밀 번호를 받아 확인한다.
     * 1. 요청한 정보를 찾을수 없습니다.
     * 2. 가존 비밀번호와 다릅니다.
     *  3.로그인 해주세요.
     * */
    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword){
        Member findMember = memberRepository.findByIdOrElseThrow(id);

        if(!passwordEncoder.matches(oldPassword,findMember.getPassword())){
            throw new PasswordMismatchException( "기존 비밀 번호와 다릅니다.");
        }

        findMember.updatePassword( passwordEncoder.encode(newPassword));
    }

    /*
     * 2025 /04 /01
     * 유저 패스워드 삭제 서비스
     * 1. 요청한 정보를 찾을수 없습니다.
     * 2. 입력한 패스워드와 다릅니다.
     * 3.로그인 해주세요.
     * */
    public void memberDeleteService(Long id, String password){
        Member findMember = memberRepository.findByIdOrElseThrow(id);
        if(!passwordEncoder.matches(password, findMember.getPassword())){
            throw new BadCredentialsException("입력한 패스워드와 다릅니다.");
        }
        memberRepository.delete(findMember);
    }
}
