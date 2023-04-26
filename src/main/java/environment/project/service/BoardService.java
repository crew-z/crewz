package environment.project.service;


import environment.project.dto.BoardDTO;
import environment.project.dto.UserDTO;

import java.util.List;

public interface BoardService {
    List<BoardDTO> selectBoard();
}
