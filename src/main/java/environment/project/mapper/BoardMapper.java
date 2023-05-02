package environment.project.mapper;

import environment.project.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BoardMapper {
    // SELECT Count
    @Select("SELECT COUNT(*) FROM BOARD ")
    int selectCount();
  
    // SELECT
    @Select("SELECT * FROM BOARD bo LEFT JOIN BOARD_PERIOD bp on bo.board_no = bp.board_no")
    List<BoardDTO> selectService();

    @Select("SELECT * FROM BOARD bo LEFT JOIN BOARD_PERIOD bp on bo.board_no = bp.board_no WHERE board_title LIKE '%${boardTitle}%'")
    List<BoardDTO> selectServiceToSearch(String boardTitle);

    @Update("UPDATE board_views SET board_views = board_views +1  WHERE board_no = #{boardNo} ")
    void clickCount(Long boardNo);
}
