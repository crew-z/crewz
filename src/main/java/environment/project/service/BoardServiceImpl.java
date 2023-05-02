package environment.project.service;

import environment.project.dto.BoardDTO;
import environment.project.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

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
    public List<BoardDTO> selectBoardToSearch(String boardTitle) {
        return boardMapper.selectServiceToSearch(boardTitle);
    }

}
