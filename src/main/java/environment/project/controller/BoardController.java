package environment.project.controller;

import static environment.project.util.BoardUtil.*;
import static environment.project.util.DateUtil.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import environment.project.dto.BoardCategoryDTO;
import environment.project.dto.BoardCreateDTO;
import environment.project.dto.BoardGetDTO;
import environment.project.dto.BoardPeriodGetDTO;
import environment.project.dto.BoardReplyGetDTO;
import environment.project.dto.BoardReplyWithMetadata;
import environment.project.dto.BoardUpdateDTO;
import environment.project.dto.CategoryInfoDTO;
import environment.project.exception.ResourceNotFoundException;
import environment.project.service.BoardReplyService;
import environment.project.service.BoardService;
import environment.project.service.CategoryInfoService;
import environment.project.service.ClubInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/boards")
@RequiredArgsConstructor
@Slf4j
public class BoardController {
	private final BoardService boardService;
	private final BoardReplyService boardReplyService;
	private final HttpSession httpSession;
	private final ClubInfoService clubInfoService;
	private final CategoryInfoService categoryInfoService;

	// 동아리 상세 페이지 READ
	// GET: /boards/{boardNo}
	@GetMapping("/{boardNo}")
	public String getBoardByBoardNo(@PathVariable String boardNo, Model model) {
		Long boardNoNum = parseStringtoLong(boardNo);
		Long userNo = (Long)httpSession.getAttribute("loginUser");

		boolean isLoggedIn = userNo != null;

		// board 정보 불러오는 것
		BoardGetDTO boardGetDTO = boardService.getBoardByBoardNo(boardNoNum);
		BoardPeriodGetDTO boardPeriodGetDTO = boardService.getBoardPeriodByBoardNo(boardNoNum);
		List<BoardCategoryDTO> boardCategoryDTOS = categoryInfoService.selectBoardCategoryInfo(boardNoNum);

		boardService.clickCount(boardNoNum);

		// 예외처리: 없는 boardNo를 불러오면
		if (boardGetDTO == null) {
			throw new ResourceNotFoundException("Board not found with id: " + boardNo);
		}

		// boardNo를 가지고 있는 reply
		List<BoardReplyGetDTO> boardReplyGetDTOS = boardReplyService.getAllReplyByBoardNo(boardNoNum);
		List<BoardReplyWithMetadata> boardReplyWithMetadatas = boardReplyGetDTOS.stream()
			.map(reply -> new BoardReplyWithMetadata(reply, Objects.equals(reply.getUserNo(), userNo)))
			.collect(Collectors.toList());

		int replyCount = boardReplyGetDTOS.size();

		boolean hasApplied = clubInfoService.checkUserInClub(boardGetDTO.getClubNo(), userNo);

		// D-day 구현
		long dDay = dateDifference(boardPeriodGetDTO.getEndDate());
		if (dDay >= 0)
			model.addAttribute("dDay", dDay);

		model.addAttribute("hasApplied", hasApplied);
		model.addAttribute("board", boardGetDTO);
		model.addAttribute("replyCount", replyCount);
		model.addAttribute("boardPeriod", boardPeriodGetDTO);
		model.addAttribute("boardCategoryInfo", boardCategoryDTOS);
		model.addAttribute("replysWithMetadata", boardReplyWithMetadatas);
		model.addAttribute("isLoggedIn", isLoggedIn);
		return "board";
	}

	// 동아리 상세 내용 생성 페이지 READ
	// POST: /boards/new
	@PostMapping("/new")
	public String getBoardCreationPage(@RequestParam Long clubNo, Model model) {
		// boardTitle 가져오기
		String boardTitle = boardService.findBoardTitleByClubNo(clubNo);
		// categoryList 가져오기
		List<CategoryInfoDTO> categoryList = categoryInfoService.selectCategoryInfo();
		log.info("categoryList: {}", categoryList);
		model.addAttribute("clubNo", clubNo);
		model.addAttribute("boardTitle", boardTitle);
		model.addAttribute("categoryList", categoryList);
		return "boardCreate";
	}

	// 동아리 상세 내용 수정 페이지 READ
	// POST: /boards/edit
	@PostMapping("/edit")
	public String getBoardEditPageByBoardNo(@RequestParam Long boardNo, Model model) {
		BoardGetDTO boardGetDTO = boardService.getBoardByBoardNo(boardNo);
		List<CategoryInfoDTO> categoryList = categoryInfoService.selectCategoryInfo();
		List<BoardCategoryDTO> boardCategoryDTOS = categoryInfoService.selectBoardCategoryInfo(boardNo);

		// 예외처리: boardNo가 없는 페이지 불러올 때
		if (boardGetDTO == null) {
			throw new ResourceNotFoundException("Board not found with id: " + boardNo);
		}

		model.addAttribute("board", boardGetDTO);
		model.addAttribute("categoryList", categoryList);
		model.addAttribute("boardCategoryList", boardCategoryDTOS);
		return "boardEdit";
	}

	// 동아리 상세 페이지 CREATE
	// POST: /boards
	@PostMapping
	public String createBoard(@ModelAttribute BoardCreateDTO boardCreateDTO) {
		Long userNo = (Long)httpSession.getAttribute("loginUser");
		boardCreateDTO.setUserNo(userNo);

		int boardNo = boardService.createBoard(boardCreateDTO);
		log.debug("boardNo: {}", boardNo);

		// 예외처리: DB에 새로운 값 생성 실패했을 경우
		if (boardNo != 1) {
			throw new IllegalStateException("Failed to create board");
		} else {
			// 게시물이 정상적으로 등록 되었을 때 기간 및 카테고리 등록
			boardService.createBoardPeriod(boardCreateDTO);

			if (boardCreateDTO.getCategoryInfo().size() != 0) {
				for (Long categoryNo : boardCreateDTO.getCategoryInfo()) {
					categoryInfoService.createBoardCategory(boardCreateDTO.getBoardNo(), categoryNo);
				}
			}
		}

		return "redirect:/boards/" + boardCreateDTO.getBoardNo();

	}

	// 동아리 상세 페이지 UPDATE
	// POST: /boards/{boardNo}
	@PostMapping("/{boardNo}")
	public String updateBoardByBoarNo(@PathVariable String boardNo, BoardUpdateDTO boardUpdateDTO) {
		Long boardNoNum = parseStringtoLong(boardNo);
		List<BoardCategoryDTO> boardCategoryDTOS = categoryInfoService.selectBoardCategoryInfo(boardNoNum);
		boardUpdateDTO.setBoardNo(boardNoNum);

		// 카테고리 수정
		if (boardUpdateDTO.getCategoryInfo() != null) {
			categoryInfoService.updateBoardCategoryInfo(boardNoNum, boardCategoryDTOS,
				boardUpdateDTO.getCategoryInfo());
		} else {
			categoryInfoService.allDeleteBoardCategory(boardNoNum);
		}

		// 예외처리: DB에 기존 데이터 업데이트가 제대로 되지 않았을 때
		if (!boardService.updateBoardByBoardNo(boardUpdateDTO)) {
			throw new IllegalStateException("Failed to update board with id: " + boardNo);
		}

		return "redirect:/boards/" + boardUpdateDTO.getBoardNo();

	}
}
