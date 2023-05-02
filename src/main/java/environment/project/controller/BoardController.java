package environment.project.controller;

import environment.project.dto.*;
import environment.project.exception.ResourceNotFoundException;
import environment.project.service.BoardReplyService;
import environment.project.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static environment.project.util.BoardUtil.parseboardNo;
import static environment.project.util.DateUtil.dateDifference;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;
    private final BoardReplyService boardReplyService;

    /**
     * GET: 동아리 상세 페이지 read
     *
     * @author 이권석
     */
    @GetMapping("/{boardNo}")
    public String getBoardByBoardNo(@PathVariable String boardNo, Model model) {
        Long boardNoNum = parseboardNo(boardNo);
        //BoardGetDTO boardGetDTO = boardService.getBoardByBoardNo(boardNoNum);

        // board 정보 불러오는 것
        BoardGetDTO boardGetDTO = boardService.getBoardByBoardNo(boardNoNum);

        // boardNo를 가지고 있는 reply
        List<BoardReplyGetDTO> boardReplyGetDTOS = boardReplyService.getAllReplyByBoardNo(boardNoNum);
        int replyCount = boardReplyGetDTOS.size();

        // 404 예외처리
        if (boardGetDTO == null) {
            throw new ResourceNotFoundException("Board not found with id: " + boardNo);
        }

        long dDay = dateDifference(boardGetDTO.getEndDate());
        if (dDay >= 0) model.addAttribute("dDay", dDay);
        model.addAttribute("board", boardGetDTO);
        model.addAttribute("replys", boardReplyGetDTOS);
        model.addAttribute("replyCount", replyCount);

        return "board";
    }

    /**
     * GET: 동아리 new 상세 페이지 read
     * @author 이권석
     */
    @GetMapping("/new")
    public String getBoardCreationPage() {
        return "boardCreate";
    }

    /**
     * GET: 동아리 상세 수정 페이지 read
     *
     * @author 이권석
     */
    @GetMapping("/edit/{boardNo}")
    public String getBoardEditPageByBoardNo(
            @PathVariable String boardNo,
            Model model) {
        Long boardNoNum = parseboardNo(boardNo);
        BoardGetDTO boardGetDTO = boardService.getBoardByBoardNo(boardNoNum);

        // 404 예외처리
        if (boardGetDTO == null) {
            throw new ResourceNotFoundException("Board not found with id: " + boardNo);
        }
        model.addAttribute("board", boardGetDTO);
        return "boardEdit";
    }

    /**
     * POST: 동아리 상세 페이지 create
     *
     * @author 이권석
     */
    @PostMapping
    public String createBoard(@ModelAttribute BoardCreateDTO boardCreateDTO) {
        boardCreateDTO.setClubNo(1L);
        boardCreateDTO.setUserNo(1L);
        int boardNo = boardService.createBoard(boardCreateDTO);

        // 예외처리: insert가 실패했을 경우
        if (boardNo != 1) {
            throw new IllegalStateException("Failed to create board");
        }
        return "redirect:/boards/" + boardCreateDTO.getBoardNo();
    }

    /**
     * POST: 동아리 상세 페이지 update
     *
     * @author 이권석
     */
    @PostMapping("/{boardNo}")
    public String updateBoardByBoarNo(@PathVariable String boardNo, BoardUpdateDTO boardUpdateDTO) {
        Long boardNoNum = parseboardNo(boardNo);
        boardUpdateDTO.setBoardNo(boardNoNum);

        // 예외처리: 업뎃이 제대로 되지 않았을 때
        if(!boardService.updateBoardByBoardNo(boardUpdateDTO)) {
            throw new IllegalStateException("Failed to update board with id: " + boardNo);
        }

        return "redirect:/boards/" + boardUpdateDTO.getBoardNo();
    }
}
