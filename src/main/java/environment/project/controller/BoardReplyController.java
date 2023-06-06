package environment.project.controller;

import static environment.project.util.BoardUtil.*;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import environment.project.dto.BoardReplyCreateDTO;
import environment.project.dto.BoardReplyUpdateDTO;
import environment.project.service.BoardReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/boards/{boardNo}/replys")
@RequiredArgsConstructor
@Slf4j
public class BoardReplyController {
	private final BoardReplyService boardReplyService;
	private final HttpSession httpSession;

	// 리뷰 작성 CREATE
	// POST: /boards/{boardNo}/replys
	@PostMapping
	public String createReplyByBoardNo(@PathVariable String boardNo,
		@ModelAttribute BoardReplyCreateDTO boardReplyCreateDTO) {
		Long boardNoNum = parseStringtoLong(boardNo);
		Long userNo = (Long)httpSession.getAttribute("loginUser");
		boardReplyCreateDTO.setUserNo(userNo);
		boardReplyCreateDTO.setClubNo(boardReplyCreateDTO.getClubNo());
		boardReplyCreateDTO.setBoardNo(boardNoNum);

		// 예외처리: 데이터 값이 잘 들어가지 않았을 때
		int insertReply = boardReplyService.createReplyByBoardNo(boardReplyCreateDTO);

		if (insertReply != 1) {
			throw new IllegalStateException("Failed to create reply");
		}

		return "redirect:/boards/" + boardNo;
	}

	// 리뷰 수정 UPDATE
	// POST: /boards/{boardNo}/replys/{replyNo}
	@PostMapping("/{replyNo}")
	public String updateReplyByReplyNo(@PathVariable String boardNo,
		@PathVariable String replyNo,
		@RequestBody BoardReplyUpdateDTO boardReplyUpdateDTO) {
		Long boardNoNum = parseStringtoLong(boardNo);
		Long replyNoNum = parseStringtoLong(replyNo);
		boardReplyUpdateDTO.setReplyNo(replyNoNum);
		boardReplyUpdateDTO.setBoardNo(boardNoNum);

		boardReplyService.updateReplyByReplyNo(boardReplyUpdateDTO);
		return "redirect:/boards/" + boardNo;
	}

	// 댓글 삭제 DELETE
	// POST: /boards/{boardNo}/replys/{replyNo}
	@PostMapping("delete/{replyNo}")
	public ResponseEntity<Void> deleteReplyByReplyNo(@PathVariable String boardNo, @PathVariable String replyNo) {
		Long boardNoNum = parseStringtoLong(boardNo);
		Long replyNoNum = parseStringtoLong(replyNo);

		boardReplyService.deleteReplyByReplyNo(boardNoNum, replyNoNum);
		return ResponseEntity.ok().build();
	}
}
