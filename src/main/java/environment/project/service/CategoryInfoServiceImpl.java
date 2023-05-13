package environment.project.service;

import environment.project.dto.BoardCategoryDTO;
import environment.project.dto.CategoryInfoDTO;
import environment.project.mapper.CategoryInfoMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryInfoServiceImpl implements CategoryInfoService{

    private final CategoryInfoMapper categoryInfoMapper;

    @Override
    public List<CategoryInfoDTO> selectCategoryInfo() {
        return categoryInfoMapper.selectCategoryInfo();
    }

    @Override
    public List<BoardCategoryDTO> selectBoardCategoryInfo(Long boardNo) {
        return categoryInfoMapper.selectBoardCategoryInfo(boardNo);
    }

    @Override
    public void updateBoardCategoryInfo(Long boardNo,List<BoardCategoryDTO> boardCategoryDTOS, List<Long> categoryInfo) {

        log.info("boardNo: {}",boardNo);
        HashMap<Long,String> dbCategoryList = new HashMap<Long, String>();
        List<Long> containCategoryNo = new ArrayList<>();

        for(BoardCategoryDTO category : boardCategoryDTOS){
            dbCategoryList.put(category.getCategoryNo(),"false");

            if(categoryInfo.contains(category.getCategoryNo())){
                containCategoryNo.add(category.getCategoryNo());
            }
        }

        log.info("dbCategoryBeforeList: {}",dbCategoryList);

        List<Long> insertCategoryNo = new ArrayList<>(categoryInfo);
        insertCategoryNo.removeAll(containCategoryNo);

        for(Long insertCateNo : insertCategoryNo){
            dbCategoryList.put(insertCateNo,"true");
            categoryInfoMapper.createBoardCategory(boardNo,insertCateNo);
        }

        for (Map.Entry<Long, String> entry : dbCategoryList.entrySet()) {
            if(categoryInfo.contains(entry.getKey())){
                dbCategoryList.put(entry.getKey(),"true");
            }
            if(entry.getValue().equals("false")){
                categoryInfoMapper.deleteBoardCategory(boardNo, entry.getKey());
            }
        }

        log.info("dbCategoryAfterList: {}",dbCategoryList);

    }

    @Override
    public void createBoardCategory(Long boardNo, Long categoryNo) {
        categoryInfoMapper.createBoardCategory(boardNo, categoryNo);
    }

    @Override
    public void allDeleteBoardCategory(Long boardNo) {
        categoryInfoMapper.allDeleteBoardCategory(boardNo);
    }
}
