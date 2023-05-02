package environment.project.service;

import environment.project.dto.BoardCreateDTO;
import environment.project.dto.BoardDTO;
import environment.project.dto.BoardUpdateDTO;
import environment.project.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public List<BoardDTO> selectBoard() {
        return boardMapper.selectService();
    }

    @Override
    public BoardDTO getBoardById(Long boardNo) {
        return boardMapper.getBoardById(boardNo);
    }
    @Override
    public int createBoard(BoardCreateDTO boardCreateDTO) {
        return boardMapper.createBoard(boardCreateDTO);
    }

    @Override
    public boolean updateBoardByBoardNo(BoardUpdateDTO boardUpdateDTO) {
        int rowsAffected = boardMapper.updateBoardByBoardNo(boardUpdateDTO);
        return rowsAffected > 0;
    }
}
