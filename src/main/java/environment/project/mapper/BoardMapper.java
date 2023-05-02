package environment.project.mapper;

import environment.project.dto.BoardCreateDTO;
import environment.project.dto.BoardDTO;
import environment.project.dto.BoardUpdateDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    // SELECT
    @Select("SELECT * FROM BOARD bo LEFT JOIN BOARD_PERIOD bp on bo.board_no = bp.board_no") // sql문 그대로 작성
    List<BoardDTO> selectService();

    /**
     * GET: 동아리 상세 페이지 read
     * @author 이권석
     */
    @Select("SELECT * FROM board WHERE board_no = #{boardNo}")
    BoardDTO getBoardById(Long boardNo);

    /**
     * POST: 동아리 상세 페이지 create
     * @author 이권석
     */
    @Insert("INSERT INTO board (user_no, club_no, board_title, board_pre_content, board_content, board_active_content, board_regular_content, board_etc_content, regdate) VALUES (#{userNo}, #{clubNo}, #{boardTitle}, #{boardPreContent}, #{boardContent}, #{boardActiveContent}, #{boardRegularContent}, #{boardEtcContent}, now())")
    @Options(useGeneratedKeys = true, keyProperty = "boardNo", keyColumn="board_no")
    int createBoard(BoardCreateDTO boardCreateDTO);

    /**
     * PATCH: 동아리 상세 페이지 update
     * @author 이권석
     */
    @Update("UPDATE board SET board_title = #{boardTitle}, board_pre_content = #{boardPreContent}, board_content = #{boardContent}, board_active_content = #{boardActiveContent}, board_regular_content = #{boardRegularContent}, board_etc_content = #{boardEtcContent} WHERE board_no = #{boardNo}")
    int updateBoardByBoardNo(BoardUpdateDTO boardUpdateDTO);

}
