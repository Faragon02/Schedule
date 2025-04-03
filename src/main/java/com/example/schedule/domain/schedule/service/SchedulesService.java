package com.example.schedule.domain.schedule.service;

import com.example.schedule.domain.schedule.dto.ScheduleResponseDto;
import com.example.schedule.domain.schedule.entity.Schedule;
import com.example.schedule.domain.schedule.repository.ScheduleRepository;
import com.example.schedule.domain.members.entity.Member;
import com.example.schedule.domain.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulesService {

    private final MemberRepository memberRepository;
    private final ScheduleRepository scheduleRepository;


    /*
    * 2025 04 04
    * 일정 생성
    * 제목, 내용 만 입력 받고 글Id~수정일까지 전부 리턴
    *  */
    public ScheduleResponseDto scheduleSaveService(String title, String contents, Long id){
        Member findMember = memberRepository.findByIdOrElseThrow(id);
        Schedule schedule = new Schedule( title, contents, findMember.getUserName());
        schedule.setMember(findMember);
        scheduleRepository.save(schedule);
        return new ScheduleResponseDto(schedule);
    }

    /*
     * 2025 04 04
     * 일정  조회
     * 해당 글 ID 입력
     *  글Id~수정일까지 전부 리턴
     *  */

    public ScheduleResponseDto scheduleFindByIdService(Long id){
        Schedule findschedule = scheduleRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(findschedule);
    }

    /*
     * 2025 04 04
     * 일정  조회
     * 해당 글ID, 수정할 내용 입력
     *  글Id~수정일까지 전부 리턴
     *  */
    public ScheduleResponseDto scheduleUpdateService(Long id , String contents){

        Schedule findschedule = scheduleRepository.findByIdOrElseThrow(id);
        findschedule.update(contents);
        //scheduleRepository.save(findBoard);
        return new ScheduleResponseDto(findschedule);
    }

    /*
     * 2025 04 04
     * 일정  조회
     * 해당 글ID, 수정할 내용 입력
     *  글Id~수정일까지 전부 리턴
     *  */
    public void scheduleDeleteService(Long id){
        Schedule findschedule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(findschedule);
    }
}
