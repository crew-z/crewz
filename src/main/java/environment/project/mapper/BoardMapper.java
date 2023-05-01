package environment.project.mapper;

import environment.project.dto.BoardDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface BoardMapper {
    // SELECT
    @Select("SELECT * FROM BOARD bo LEFT JOIN BOARD_PERIOD bp on bo.board_no = bp.board_no") // sql문 그대로 작성
    List<BoardDTO> selectService();

}
