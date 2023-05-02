package environment.project.mapper;

import environment.project.dto.BoardCreateDTO;
import environment.project.dto.BoardDTO;
import environment.project.dto.BoardGetDTO;
import environment.project.dto.BoardUpdateDTO;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface BoardMapper {
    // SELECT Count
    @Select("SELECT COUNT(*) FROM BOARD ")
    int selectCount();
  
    // SELECT
    @Select("SELECT * FROM BOARD bo LEFT JOIN BOARD_PERIOD bp on bo.board_no = bp.board_no")
    List<BoardDTO> selectService();


    /**
     * GET: 동아리 상세 페이지 read
     * @author 이권석
     */
    @Select("SELECT b.*, bp.start_date, bp.end_date FROM board b JOIN board_period bp ON b.board_no = bp.board_no WHERE b.board_no = #{boardNo}")
    BoardGetDTO getBoardByBoardNo(Long boardNo);
//    @Select("SELECT * FROM board WHERE board_no = #{boardNo};")
//    BoardGetDTO getBoardByBoardNo(Long boardNo);
//    @Select("SELECT b.board_no, b.board_title, b.board_content, b.regdate, br.reply_no, br.reply_content, br.regdate AS reply_regdate FROM board AS b LEFT JOIN board_reply AS br ON b.board_no = br.board_no WHERE b.board_no = 3;")
//    BoardGetDTO getBoardByBoardNo(Long boardNo);

//    @Select("SELECT * FROM board WHERE board_no = #{boardNo}")
//    BoardGetDTO getBoardByBoardNo(Long boardNo);


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

    @Select("SELECT * FROM BOARD bo LEFT JOIN BOARD_PERIOD bp on bo.board_no = bp.board_no WHERE board_title LIKE '%${boardTitle}%'")
    List<BoardDTO> selectServiceToSearch(String boardTitle);

    @Update("UPDATE board_views SET board_views = board_views +1  WHERE board_no = #{boardNo} ")
    void clickCount(Long boardNo);

}
