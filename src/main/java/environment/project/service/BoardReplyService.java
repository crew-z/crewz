package environment.project.service;

import environment.project.dto.BoardReplyCreateDTO;
import environment.project.dto.BoardReplyGetDTO;
import environment.project.dto.BoardReplyUpdateDTO;

import java.util.List;

public interface BoardReplyService {

    List<BoardReplyGetDTO> getAllReplyByBoardNo(Long boardNo);
    int createReplyByBoardNo(BoardReplyCreateDTO boardReplyCreateDTO);

    boolean updateReplyByReplyNo(BoardReplyUpdateDTO boardReplyUpdateDTO);
}
