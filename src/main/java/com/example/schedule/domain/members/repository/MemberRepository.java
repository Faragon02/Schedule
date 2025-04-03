package com.example.schedule.domain.members.repository;

import com.example.schedule.domain.members.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByUserName(String userName);

    default Member findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Does not exist id = " + id)
        );
    }
    default Member findMemberByUsernameOrElseTrow(String userName){
        return findMemberByUserName(userName).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Does not exist username = " + userName));
    }

    default Member findByLoginOrElseTrow(String userName, String password){
        return  findMemberByUserName( userName)
                .filter(Member -> Member.getUserName().equals(userName) && Member.getPassword().equals(password))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist username = " + userName));
    }
}
