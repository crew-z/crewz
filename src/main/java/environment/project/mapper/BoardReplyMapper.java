package environment.project.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import environment.project.dto.BoardReplyCreateDTO;
import environment.project.dto.BoardReplyGetDTO;
import environment.project.dto.BoardReplyUpdateDTO;

@Mapper
public interface BoardReplyMapper {
	@Select("SELECT \n" +
		"    br.reply_no, br.board_no, br.user_no, br.club_no, br.reply_content, \n" +
		"    br.regdate, br.updatedate, u.user_id, u.user_name\n" +
		"FROM board_reply AS br\n" +
		"JOIN user AS u ON br.user_no = u.user_no\n" +
		"WHERE br.board_no = #{boardNo}")
	List<BoardReplyGetDTO> getAllReplyByBoardNo(Long boardNo);

	@Insert("INSERT INTO board_reply (board_no, user_no, club_no, reply_content, regdate) VALUES (#{boardNo}, #{userNo}, (SELECT club_no FROM board WHERE board_no = #{boardNo}), #{replyContent}, NOW())")
	int createReplyByBoardNo(BoardReplyCreateDTO boardReplyCreateDTO);

	@Update("UPDATE board_reply SET reply_content = #{replyContent}, updatedate = NOW() WHERE reply_no = #{replyNo}")
	int updateReplyByReplyNo(BoardReplyUpdateDTO boardReplyUpdateDTO);

	@Delete("DELETE FROM board_reply WHERE board_no = #{boardNo} AND reply_no = #{replyNo}")
	void deleteReplyByReplyNo(@Param("boardNo") Long boardNo, @Param("replyNo") Long replyNo);
}
