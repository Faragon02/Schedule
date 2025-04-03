package com.example.schedule.domain.schedule.service;

import com.example.schedule.domain.schedule.dto.ScheduleResponseDto;
import com.example.schedule.domain.schedule.entity.Board;
import com.example.schedule.domain.schedule.repository.BoardRepository;
import com.example.schedule.domain.members.entity.Member;
import com.example.schedule.domain.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public ScheduleResponseDto scheduleSaveService(String title, String contents, String writer){
        Member findMember = memberRepository.findMemberByUsernameOrElseTrow(writer);
        Board board = new Board( title, contents, writer);
        board.setMember(findMember);
        boardRepository.save(board);

        return new ScheduleResponseDto(board);
    }
    public ScheduleResponseDto scheduleFindByIdService(Long id){
        Board findBoard = boardRepository.findByIdOrElseThrow(id);
        return new ScheduleResponseDto(findBoard);
    }

    public ScheduleResponseDto scheduleUpdateService(Long id , String contents){

        Board findBoard = boardRepository.findByIdOrElseThrow(id);
        findBoard.update(contents);
        boardRepository.save(findBoard);
        return new ScheduleResponseDto(findBoard.getId(), findBoard.getTitle(), findBoard.getContents(),findBoard.getWriter());
    }



    public void scheduleDeleteService(Long id){
        Board findBoard = boardRepository.findByIdOrElseThrow(id);
        boardRepository.delete(findBoard);
    }
}
