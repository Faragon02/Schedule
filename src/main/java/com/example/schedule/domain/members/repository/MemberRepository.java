package com.example.schedule.domain.members.repository;

import com.example.schedule.config.PasswordEncoder;
import com.example.schedule.domain.members.entity.Member;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByUserName(String userName);
    Optional<Member> findMemberByEmail(String email);

    default Member findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(() -> new EntityNotFoundException("요청한 정보를 찾을 수 없습니다.")
        );
    }

    //사용 하지 않음.
    default Member findMemberByUsernameOrElseTrow(String userName){
        return findMemberByUserName(userName).orElseThrow(
                () -> new EntityNotFoundException("요청한 정보를 찾을 수 없습니다."));
    }

    default Member findByLoginOrElseTrow(String email, String password){
        return findMemberByEmail(email).filter(Member -> Member.getEmail().equals(email) )
                .orElseThrow(()-> new EntityNotFoundException("요청한 정보를 찾을 수 없습니다."));
    }

    default boolean isExist(String email){
        return findMemberByEmail( email).stream().anyMatch(member -> member.getEmail().equals(email));
    }
}
