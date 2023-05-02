package environment.project.service;

import environment.project.dto.BoardCreateDTO;
import environment.project.dto.BoardDTO;
import environment.project.dto.BoardGetDTO;
import environment.project.dto.BoardUpdateDTO;

import java.util.List;

public interface BoardService {
    int selectBoardCount();
    List<BoardDTO> selectBoard();

    // GET : 동아리 상세 페이지 read
    BoardGetDTO getBoardByBoardNo(Long boardNo);

    // POST : 동아리 상세 페이지 create
    int createBoard(BoardCreateDTO boardCreateDTO);

    // PATCH : 동아리 상세 페이지 update
    boolean updateBoardByBoardNo(BoardUpdateDTO boardUpdateDTO);

    List<BoardDTO> selectBoardToSearch(String boardTitle);

    void clickCount(Long boardNo);

}
