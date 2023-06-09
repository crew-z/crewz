package environment.project.service;

import java.util.List;

import environment.project.dto.BoardCreateDTO;
import environment.project.dto.BoardDTO;
import environment.project.dto.BoardGetDTO;
import environment.project.dto.BoardPeriodGetDTO;
import environment.project.dto.BoardUpdateDTO;

public interface BoardService {
	int selectBoardCount();

	List<BoardDTO> selectBoard();

	BoardGetDTO getBoardByBoardNo(Long boardNo);

	int createBoard(BoardCreateDTO boardCreateDTO);

	int createBoardPeriod(BoardCreateDTO boardCreateDTO);

	boolean updateBoardByBoardNo(BoardUpdateDTO boardUpdateDTO);

	List<BoardDTO> selectBoardToSearch(String boardTitle);

	List<BoardDTO> selectRecrutingBoard();

	List<BoardDTO> selectRecrutingBoardToSort();

	void clickCount(Long boardNo);

	BoardPeriodGetDTO getBoardPeriodByBoardNo(Long boardNo);

	String findBoardTitleByClubNo(Long clubNo);

	boolean isClubMember(Long userNo, Long boardNoNum);
}
