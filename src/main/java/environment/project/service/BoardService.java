package environment.project.service;

import environment.project.dto.*;

import java.util.List;

public interface BoardService {
    int selectBoardCount();
    List<BoardDTO> selectBoard();
    BoardGetDTO getBoardByBoardNo(Long boardNo);
    int createBoard(BoardCreateDTO boardCreateDTO);

    int createBoardPeriod(BoardCreateDTO boardCreateDTO);
    boolean updateBoardByBoardNo(BoardUpdateDTO boardUpdateDTO);
    List<BoardDTO> selectBoardToSearch(String boardTitle);
    void clickCount(Long boardNo);

    BoardPeriodGetDTO getBoardPeriodByBoardNo(Long boardNo);

}
