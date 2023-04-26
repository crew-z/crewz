package environment.project.controller;

import environment.project.dto.BoardDTO;
import environment.project.dto.UserDTO;
import environment.project.service.BoardService;
import environment.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.min;

@Controller
public class MainController {
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;
    @GetMapping(path = { "/", "/main" })
    public String main(Model model) {
        List<UserDTO> list = userService.selectUser();
        List<BoardDTO> boardList = boardService.selectBoard();
        int limit = 4;
        ArrayList<Object> arrBoardList = new ArrayList<>();
        for (int id = 0; id < boardList.size(); id += limit) {

            arrBoardList.add(new ArrayList<>(boardList.subList(id, min(id + limit, boardList.size()))));

        }
        model.addAttribute("list", list);
        model.addAttribute("arrBoardList", arrBoardList);
        return "main";
    }
}

