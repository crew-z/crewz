package environment.project.controller;

import static java.lang.Math.*;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import environment.project.dto.BoardDTO;
import environment.project.dto.UserDTO;
import environment.project.service.BoardService;
import environment.project.service.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MainController {
	@Autowired
	private UserService userService;
	@Autowired
	private BoardService boardService;

	@GetMapping(path = {"/", "/main"})
	public String mainPage(Model model, HttpSession session) {
		List<UserDTO> list = userService.selectUser();
		List<BoardDTO> boardList = boardService.selectRecrutingBoard();
		int limit = 4;
		ArrayList<Object> arrBoardList = new ArrayList<>();
		for (int id = 0; id < boardList.size(); id += limit) {
			arrBoardList.add(new ArrayList<>(boardList.subList(id, min(id + limit, boardList.size()))));
		}
		List<BoardDTO> sortBoardList = boardService.selectRecrutingBoardToSort();
		ArrayList<Object> arrSortBoardList = new ArrayList<>();
		for (int id = 0; id < sortBoardList.size(); id += limit) {
			arrSortBoardList.add(new ArrayList<>(sortBoardList.subList(id, min(id + limit, sortBoardList.size()))));
		}
		// 회원가입 후 로그인
		Long userNo = (Long)session.getAttribute("loginUser");

		if (userNo != null) {
			UserDTO userDTO = userService.getUserByUserNo(userNo);
			model.addAttribute("user", userDTO);
		}

		model.addAttribute("list", list);
		model.addAttribute("arrBoardList", arrBoardList);
		model.addAttribute("arrSortBoardList", arrSortBoardList);
		return "main";
	}

	@RequestMapping("/all")
	public String allBoardListToSearch(@RequestParam(required = false) String searchValue, Model model) {
		List<UserDTO> list = userService.selectUser();
		List<BoardDTO> boardListToSearch = boardService.selectBoardToSearch(searchValue);

		model.addAttribute("list", list);
		model.addAttribute("boardList", boardListToSearch);
		model.addAttribute("boardCount", boardListToSearch.size());
		model.addAttribute("searchValue", searchValue);
		
		return "all";
	}

}

