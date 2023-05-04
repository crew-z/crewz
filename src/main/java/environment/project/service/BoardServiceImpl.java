package environment.project.service;

import environment.project.dto.*;
import environment.project.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public int selectBoardCount() {
        return boardMapper.selectCount();
    }

    @Override
    public List<BoardDTO> selectBoard() {
        return boardMapper.selectService();
    }

    @Override
    public BoardGetDTO getBoardByBoardNo(Long boardNo) {
        return boardMapper.getBoardByBoardNo(boardNo);
    }
    
    @Override
    public int createBoard(BoardCreateDTO boardCreateDTO) {
        return boardMapper.createBoard(boardCreateDTO);
    }

    @Override
    public int createBoardPeriod(BoardCreateDTO boardCreateDTO) {
        return boardMapper.createBoardPeriod(boardCreateDTO);
    }

    @Override
    public boolean updateBoardByBoardNo(BoardUpdateDTO boardUpdateDTO) {
        int rowsAffected = boardMapper.updateBoardByBoardNo(boardUpdateDTO);
        return rowsAffected > 0;
    }

    @Override
    public BoardPeriodGetDTO getBoardPeriodByBoardNo(Long boardNo) {
        return boardMapper.getBoardPeriodByBoardNo(boardNo);
    }

    @Override
    public List<BoardDTO> selectBoardToSearch(String boardTitle) {
        return boardMapper.selectServiceToSearch(boardTitle);
    }

    @Override
    public void clickCount(Long boardNo) {
        boardMapper.clickCount(boardNo);
    }
    
}
