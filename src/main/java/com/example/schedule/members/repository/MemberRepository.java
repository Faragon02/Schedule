package com.example.schedule.members.repository;

import com.example.schedule.members.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findMemberByUsername(String username);

    default Member findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Does not exist id = " + id)
        );
    }
    default Member findMemberByUsernameOrElseTrow(String username){
        return findMemberByUsername(username).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Does not exist username = " + username));
    }

}
