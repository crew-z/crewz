package environment.project.service;

import environment.project.dto.BoardReplyCreateDTO;
import environment.project.dto.BoardReplyGetDTO;
import environment.project.dto.BoardReplyUpdateDTO;
import environment.project.mapper.BoardReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardReplyServiceImpl implements BoardReplyService {
    private final BoardReplyMapper boardReplyMapper;

    @Override
    public List<BoardReplyGetDTO> getAllReplyByBoardNo(Long boardNo) {
        return boardReplyMapper.getAllReplyByBoardNo(boardNo);
    }

    @Override
    public int createReplyByBoardNo(BoardReplyCreateDTO boardReplyCreateDTO) {
        return boardReplyMapper.createReplyByBoardNo(boardReplyCreateDTO);
    }

    @Override
    public boolean updateReplyByReplyNo(BoardReplyUpdateDTO boardReplyUpdateDTO) {
        int rowsAffected =  boardReplyMapper.updateReplyByReplyNo(boardReplyUpdateDTO);
        return rowsAffected > 0;
    }

    @Override
    public void deleteReplyByReplyNo(Long boardNo, Long replyNo) {
        boardReplyMapper.deleteReplyByReplyNo(boardNo, replyNo);
    }
}
