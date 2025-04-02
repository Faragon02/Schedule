package com.example.schedule.members.repository;

import com.example.schedule.members.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByUserName(String userName);

   default Member findByLoginOrElseTrow(String userName, String password){
       return  findByUserName( userName)
               .filter(Member -> Member.getUserName().equals(userName) && Member.getPassword().equals(password))
               .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist username = " + userName));
   }
}
