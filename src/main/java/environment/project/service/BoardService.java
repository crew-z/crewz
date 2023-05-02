package environment.project.service;
import environment.project.dto.BoardDTO;
import java.util.List;

public interface BoardService {
    int selectBoardCount();
    List<BoardDTO> selectBoard();

    List<BoardDTO> selectBoardToSearch(String boardTitle);
}
