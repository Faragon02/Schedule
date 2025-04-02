package com.example.schedule.board.service;

import com.example.schedule.board.dto.BoardResponseDto;
import com.example.schedule.board.entity.Board;
import com.example.schedule.board.repository.BoardRepository;
import com.example.schedule.members.entity.Member;
import com.example.schedule.members.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;

    public BoardResponseDto scheduleSaveService(String title, String contents, String writer){
        Member findMember = memberRepository.findMemberByUsernameOrElseTrow(writer);
        Board board = new Board( title, contents, writer);
        board.setMember(findMember);
        boardRepository.save(board);

        return new BoardResponseDto(board);
    }
    public BoardResponseDto scheduleFindByIdService(Long id){
        Board findBoard = boardRepository.findByIdOrElseThrow(id);

        return new BoardResponseDto(findBoard);
    }

    public BoardResponseDto scheduleUpdateService(Long id , String contents){

        Board findBoard = boardRepository.findByIdOrElseThrow(id);
        findBoard.update(contents);
        boardRepository.save(findBoard);
        return new BoardResponseDto(findBoard.getId(), findBoard.getTitle(), findBoard.getContents(),findBoard.getWriter());
    }



    public void scheduleDeleteService(Long id){
        Board findBoard = boardRepository.findByIdOrElseThrow(id);
        boardRepository.delete(findBoard);
    }
}
