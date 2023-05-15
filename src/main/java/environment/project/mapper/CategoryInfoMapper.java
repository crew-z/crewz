package environment.project.mapper;

import environment.project.dto.BoardCategoryDTO;
import environment.project.dto.CategoryInfoDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryInfoMapper {

    @Select(" SELECT category_no,category_name,category_delete_flag,category_create_date FROM category_info WHERE category_delete_flag = 'N' ")
    List<CategoryInfoDTO> selectCategoryInfo();

    @Select(" SELECT idx,board_no,bc.category_no,category_name,category_delete_flag " +
            "FROM board_category bc " +
            "LEFT JOIN category_info ci ON bc.category_no = ci.category_no " +
            "WHERE board_no = #{boardNo} ")
    List<BoardCategoryDTO> selectBoardCategoryInfo(Long boardNo);

    @Insert("INSERT INTO board_category (board_no, category_no) VALUES (#{boardNo}, #{categoryNo})")
    void createBoardCategory(@Param("boardNo") Long boardNo,@Param("categoryNo") Long categoryNo);

    @Delete(" DELETE FROM board_category WHERE board_no = #{ boardNo } AND category_no = #{ categoryNo } ")
    void deleteBoardCategory(@Param("boardNo") Long boardNo,@Param("categoryNo") Long categoryNo);

    @Delete(" DELETE FROM board_category WHERE board_no = #{ boardNo } ")
    void allDeleteBoardCategory(Long boardNo);
}
