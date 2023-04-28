package environment.project.service;


import environment.project.dto.BoardDTO;

import java.util.List;

public interface BoardService {
    List<BoardDTO> selectBoard();
}
