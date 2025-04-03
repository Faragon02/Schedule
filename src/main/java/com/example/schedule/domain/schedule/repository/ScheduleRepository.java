package com.example.schedule.domain.schedule.repository;

import com.example.schedule.domain.schedule.entity.Schedule;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule,Long> {
    Optional<Schedule> findById(Long id);

    default Schedule findByIdOrElseThrow(Long id){
        return findById(id).orElseThrow(()-> new EntityNotFoundException("요청한 정보를 찾을 수 없습니다."));
    }
}
