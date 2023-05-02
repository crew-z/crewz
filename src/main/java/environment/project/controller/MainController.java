package environment.project.controller;

import environment.project.dto.BoardDTO;
import environment.project.dto.UserDTO;
import environment.project.service.BoardService;
import environment.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import javax.servlet.http.HttpSession;
import static java.lang.Math.min;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;
@Slf4j
@Controller
@Slf4j
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;
    @GetMapping(path = { "/", "/main" })

    public String mainPage(Model model, HttpSession session) {
        List<UserDTO> list = userService.selectUser();
        List<BoardDTO> boardList = boardService.selectBoard();
        int limit = 4;
        ArrayList<Object> arrBoardList = new ArrayList<>();
        for (int id = 0; id < boardList.size(); id += limit) {
            arrBoardList.add(new ArrayList<>(boardList.subList(id, min(id + limit, boardList.size()))));
        }
        // 회원가입 후 로그인
        Long userNo = (Long) session.getAttribute("loginUser");

        if (userNo != null){
            UserDTO userDTO = userService.getUserByUserNo(userNo);
            model.addAttribute("user", userDTO);
            return "main";
        }

        model.addAttribute("list", list);
        model.addAttribute("arrBoardList", arrBoardList);
        return "main";
    }

    @RequestMapping("/all")
    public String allBoardListToSearch(@RequestParam(required = false) String searchValue, Model model){
        List<UserDTO> list = userService.selectUser();
        List<BoardDTO> boardListToSearch = boardService.selectBoardToSearch(searchValue);

        model.addAttribute("list", list);
        model.addAttribute("boardList", boardListToSearch);
        model.addAttribute("boardCount",boardListToSearch.size());
        log.info("boardList: {}",boardListToSearch);
        log.info("boardCount: {}",boardListToSearch.size());

        return "all";
    }
  
}

