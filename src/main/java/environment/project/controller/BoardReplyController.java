package environment.project.controller;


import environment.project.dto.BoardReplyCreateDTO;
import environment.project.dto.BoardReplyUpdateDTO;
import environment.project.service.BoardReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import static environment.project.util.BoardUtil.parseboardNo;

@Controller
@RequestMapping("/boards/{boardNo}/replys")
@RequiredArgsConstructor
@Slf4j
public class BoardReplyController {
    private final BoardReplyService boardReplyService;

    /**
     * POST: 리뷰 작성 create
     *
     * @author 이권석
     */
    @PostMapping
    public String createReplyByBoardNo(@PathVariable String boardNo,
                                       @ModelAttribute BoardReplyCreateDTO boardReplyCreateDTO) {
        Long boardNoNum = parseboardNo(boardNo);
        boardReplyCreateDTO.setUserNo(1L);
        boardReplyCreateDTO.setClubNo(1L);
        boardReplyCreateDTO.setBoardNo(boardNoNum);
        int insertReply = boardReplyService.createReplyByBoardNo(boardReplyCreateDTO);

        if (insertReply != 1) {
            throw new IllegalStateException("Failed to create reply");
        }
        return "redirect:/boards/" + boardNo;
    }

    /**
     * POST: 리뷰 수정 update
     *
     * @author 이권석
     */
    @PostMapping("/{replyNo}")
    public String updateReplyByReplyNo(@PathVariable String boardNo,
                                       @PathVariable String replyNo,
                                       BoardReplyUpdateDTO boardReplyUpdateDTO) {
        return "";
    }


    // 댓글 삭제
    /**
     * POST: 리뷰 수정 update
     *
     * @author 이권석
     */
    @PostMapping("/delete/{replyNo}")
    public String deleteReplyByReplyNo() {
        return "";
    }
}
