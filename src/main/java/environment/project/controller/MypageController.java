package environment.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MypageController {
    @GetMapping(path = {  "/mypageMain" })
    public String mypageMain() {
        return "mypage/mypageMain";
    }
}
