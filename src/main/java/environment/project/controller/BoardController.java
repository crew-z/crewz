package environment.project.controller;

import environment.project.dto.BoardCreateDTO;
import environment.project.dto.BoardDTO;
import environment.project.dto.BoardUpdateDTO;
import environment.project.exception.ResourceNotFoundException;
import environment.project.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
    private final BoardService boardService;

    /**
     * GET: 동아리 상세 페이지 read
     *
     * @author 이권석
     */
    @GetMapping("/{boardNo}")
    public String getBoardByBoardNo(@PathVariable String boardNo, Model model) {
        Long boardNoNum = parseboardNo(boardNo);
        BoardDTO boardDTO = boardService.getBoardById(boardNoNum);

        // 404 예외처리
        if (boardDTO == null) {
            throw new ResourceNotFoundException("Board not found with id: " + boardNo);
        }
        model.addAttribute("board", boardDTO);
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
        BoardDTO boardDTO = boardService.getBoardById(boardNoNum);

        // 404 예외처리
        if (boardDTO == null) {
            throw new ResourceNotFoundException("Board not found with id: " + boardNo);
        }
        model.addAttribute("board", boardDTO);
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

    /**
     * POST: 동아리 상세 페이지 delete
     *
     * @author 이권석
     */
    @DeleteMapping("/{boardNo}")
    public String deleteBoardByBoardNo(@PathVariable String boardNo) {
        Long boardNoNum = parseboardNo(boardNo);

        if(!boardService.deleteBoardByBoardNo(boardNoNum)) {
            throw new IllegalStateException("Failed to delete with id: " + boardNo);
        }
        System.out.println("보드 삭제 완료" + boardNo);
        return "redirect:/";
    }

    /**
     * 메서드: String -> Long 타입 변환
     *
     * @author 이권석
     */
    private Long parseboardNo(String boardNo) {
        Long boardNoNum;
        try {
            boardNoNum = Long.parseLong(boardNo);
        } catch (NumberFormatException e) {
            throw new ResourceNotFoundException("Board id must be a numeric string: " + boardNo);
        }
        return boardNoNum;
    }
}
