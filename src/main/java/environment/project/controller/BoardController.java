package environment.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {
    @GetMapping("/board")
    public String goBoard() {
        return "board/board";
    }
    // 동아리 상세페이지 컨트롤러입니다
}
