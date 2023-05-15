package environment.project.service;

import environment.project.dto.BoardCategoryDTO;
import environment.project.dto.CategoryInfoDTO;

import java.util.List;

public interface CategoryInfoService {
    List<CategoryInfoDTO> selectCategoryInfo();

    List<BoardCategoryDTO> selectBoardCategoryInfo(Long boardNo);

    void updateBoardCategoryInfo(Long boardNo,List<BoardCategoryDTO> boardCategoryDTOS, List<Long> categoryInfo);

    void createBoardCategory(Long boardNo, Long categoryNo);

    void allDeleteBoardCategory(Long boardNo);
}
