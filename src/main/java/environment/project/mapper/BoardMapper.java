package environment.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import environment.project.dto.BoardCreateDTO;
import environment.project.dto.BoardDTO;
import environment.project.dto.BoardGetDTO;
import environment.project.dto.BoardPeriodGetDTO;
import environment.project.dto.BoardUpdateDTO;

@Mapper
public interface BoardMapper {
	@Select("SELECT COUNT(board_no) FROM board")
	int selectCount();

	@Select("SELECT \n" +
		"    bo.board_no, bo.user_no, bo.club_no, bo.board_title, bo.board_pre_content,\n" +
		"    bo.board_content, bo.board_active_content, bo.board_regular_content, \n" +
		"    bo.board_etc_content, bo.board_views, bo.recruit_status, bo.regdate, \n" +
		"    bo.updatedate, bp.start_date, bp.end_date\n" +
		"FROM board AS bo\n" +
		"LEFT JOIN board_period AS bp ON bo.board_no = bp.board_no")
	List<BoardDTO> selectService();

	@Select("SELECT \n" +
		"    board_no, user_no, club_no, board_title, board_pre_content,\n" +
		"    board_content, board_active_content, board_regular_content, \n" +
		"    board_etc_content, board_views, recruit_status, regdate, updatedate\n" +
		"FROM board\n" +
		"WHERE board_no = #{boardNo}")
	BoardGetDTO getBoardByBoardNo(Long boardNo);

	@Select("SELECT start_date, end_date FROM board_period WHERE board_no = #{boardNo}")
	BoardPeriodGetDTO getBoardPeriodByBoardNo(Long boardNo);

	@Select("SELECT \n" +
		"   bo.board_no, bo.user_no, bo.club_no, bo.board_title, bo.board_pre_content,\n" +
		"   bo.board_content, bo.board_active_content, bo.board_regular_content,\n" +
		"   bo.board_etc_content, bo.board_views, bo.recruit_status, bo.regdate,\n" +
		"   bo.updatedate, bp.start_date, bp.end_date\n" +
		"   FROM board AS bo\n" +
		"   LEFT JOIN board_period AS bp ON bo.board_no = bp.board_no\n" +
		"   LEFT JOIN club AS c ON bo.club_no = c.club_no\n" +
		"   WHERE CURDATE() < end_date AND c.club_close_yn = 'N';")
	List<BoardDTO> selectRecrutingBoard();

	@Select("SELECT \n" +
		"   bo.board_no, bo.user_no, bo.club_no, bo.board_title, bo.board_pre_content,\n" +
		"   bo.board_content, bo.board_active_content, bo.board_regular_content,\n" +
		"   bo.board_etc_content, bo.board_views, bo.recruit_status, bo.regdate,\n" +
		"   bo.updatedate, bp.start_date, bp.end_date\n" +
		"   FROM board AS bo\n" +
		"   LEFT JOIN board_period AS bp ON bo.board_no = bp.board_no\n" +
		"   LEFT JOIN club AS c ON bo.club_no = c.club_no\n" +
		"   WHERE c.club_close_yn = 'N'\n" +
		"   ORDER BY board_views DESC;")
	List<BoardDTO> selectRecrutingBoardToSort();

	@Insert("INSERT INTO board (user_no, club_no, board_title, board_pre_content, board_content, board_active_content, board_regular_content, board_etc_content, regdate) VALUES (#{userNo}, #{clubNo}, #{boardTitle}, #{boardPreContent}, #{boardContent}, #{boardActiveContent}, #{boardRegularContent}, #{boardEtcContent}, now())")
	@Options(useGeneratedKeys = true, keyProperty = "boardNo", keyColumn = "board_no")
	int createBoard(BoardCreateDTO boardCreateDTO);

	@Insert("INSERT INTO board_period (board_no, user_no, start_date, end_date) VALUES (#{boardNo}, #{userNo}, #{startDate}, #{endDate})")
	int createBoardPeriod(BoardCreateDTO boardCreateDTO);

	@Update("UPDATE board SET board_title = #{boardTitle}, board_pre_content = #{boardPreContent}, board_content = #{boardContent}, board_active_content = #{boardActiveContent}, board_regular_content = #{boardRegularContent}, board_etc_content = #{boardEtcContent} WHERE board_no = #{boardNo}")
	int updateBoardByBoardNo(BoardUpdateDTO boardUpdateDTO);

	@Select("SELECT \n" +
		"    bo.board_no, bo.user_no, bo.club_no, bo.board_title, bo.board_pre_content,\n" +
		"    bo.board_content, bo.board_active_content, bo.board_regular_content, \n" +
		"    bo.board_etc_content, bo.board_views, bo.recruit_status, bo.regdate, \n" +
		"    bo.updatedate, bp.start_date, bp.end_date\n" +
		"FROM board AS bo\n" +
		"LEFT JOIN board_period AS bp ON bo.board_no = bp.board_no\n" +
		"LEFT JOIN club AS c ON bo.club_no = c.club_no\n" +
		"WHERE bo.board_title LIKE '%${boardTitle}%' AND c.club_close_yn = 'N';")
	List<BoardDTO> selectServiceToSearch(String boardTitle);

	@Update("UPDATE board SET board_views = board_views +1  WHERE board_no = #{boardNo} ")
	void clickCount(Long boardNo);

	@Select("SELECT c.club_name FROM club_apply AS c JOIN club AS cl ON c.club_apply_no = cl.club_apply_no WHERE cl.club_no = #{clubNo}")
	String findFirstByClubNo(Long clubNo);
}
