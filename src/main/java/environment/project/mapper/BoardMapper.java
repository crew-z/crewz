package environment.project.mapper;

import environment.project.dto.*;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface BoardMapper {
    @Select("SELECT COUNT(*) FROM BOARD ")
    int selectCount();
  
    @Select("SELECT * FROM BOARD bo LEFT JOIN BOARD_PERIOD bp on bo.board_no = bp.board_no")
    List<BoardDTO> selectService();

    @Select("SELECT * FROM board WHERE board_no = #{boardNo}")
    BoardGetDTO getBoardByBoardNo(Long boardNo);

    @Select("SELECT start_date, end_date FROM board_period WHERE board_no = #{boardNo}")
    BoardPeriodGetDTO getBoardPeriodByBoardNo(Long boardNo);

    @Insert("INSERT INTO board (user_no, club_no, board_title, board_pre_content, board_content, board_active_content, board_regular_content, board_etc_content, regdate) VALUES (#{userNo}, #{clubNo}, #{boardTitle}, #{boardPreContent}, #{boardContent}, #{boardActiveContent}, #{boardRegularContent}, #{boardEtcContent}, now())")
    @Options(useGeneratedKeys = true, keyProperty = "boardNo", keyColumn="board_no")
    int createBoard(BoardCreateDTO boardCreateDTO);

    @Insert("INSERT INTO board_period (board_no, user_no, start_date, end_date) VALUES (#{boardNo}, #{userNo}, #{startDate}, #{endDate})")
    int createBoardPeriod(BoardCreateDTO boardCreateDTO);


    @Update("UPDATE board SET board_title = #{boardTitle}, board_pre_content = #{boardPreContent}, board_content = #{boardContent}, board_active_content = #{boardActiveContent}, board_regular_content = #{boardRegularContent}, board_etc_content = #{boardEtcContent} WHERE board_no = #{boardNo}")
    int updateBoardByBoardNo(BoardUpdateDTO boardUpdateDTO);

    @Select("SELECT * FROM BOARD bo LEFT JOIN BOARD_PERIOD bp on bo.board_no = bp.board_no WHERE board_title LIKE '%${boardTitle}%'")
    List<BoardDTO> selectServiceToSearch(String boardTitle);

    @Update("UPDATE board_views SET board_views = board_views +1  WHERE board_no = #{boardNo} ")
    void clickCount(Long boardNo);

    // board -> club_apply
    //@Select("SELECT club_name FROM club_apply WHERE club_no = #{clubNo} LIMIT 1")

    @Select("SELECT c.club_name FROM club_apply AS c JOIN club AS cl ON c.club_apply_no = cl.club_apply_no WHERE cl.club_no = #{clubNo}")
    String findFirstByClubNo(Long clubNo);
    //BoardGetDTO findFirstByClubNo(Long clubNo);

}
