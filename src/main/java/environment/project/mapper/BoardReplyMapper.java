package environment.project.mapper;

import environment.project.dto.BoardReplyCreateDTO;
import environment.project.dto.BoardReplyGetDTO;
import environment.project.dto.BoardReplyUpdateDTO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardReplyMapper {

    /**
     * GET: 동아리 All 리뷰 read
     *
     * @author 이권석
     */
    @Select("SELECT br.*, u.user_id FROM board_reply br JOIN user u ON br.user_no = u.user_no WHERE br.board_no = #{boardNo}")
    List<BoardReplyGetDTO> getAllReplyByBoardNo(Long boardNo);

    /**
     * POST: 동아리 리뷰 생성 create
     *
     * @author 이권석
     */
    @Insert("INSERT INTO board_reply (board_no, user_no, club_no, reply_content, regdate) VALUES (#{boardNo}, #{userNo}, (SELECT club_no FROM board WHERE board_no = #{boardNo}), #{replyContent}, NOW())")
    int createReplyByBoardNo(BoardReplyCreateDTO boardReplyCreateDTO);

    /**
     * POST: 동아리 리뷰 수정 update
     *
     * @author 이권석
     */
    @Update("UPDATE board_reply SET reply_content = #{replyContent}, updatedate = NOW() WHERE reply_no = #{replyNo}")
    int updateReplyByReplyNo(BoardReplyUpdateDTO boardReplyUpdateDTO);

    @Delete("DELETE FROM board_reply WHERE board_no = #{boardNo} AND reply_no = #{replyNo}")
    void deleteReplyByReplyNo(Long boardNo, Long replyNo);
}