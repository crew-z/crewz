package environment.project.controller;

import environment.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    final UserService userService;

    @GetMapping("/login")
    public String login(HttpSession session){
        Long id = (Long) session.getAttribute("loginUser");
        if (id != null)
            return "redirect:/";
        return "login";
    }
    @PostMapping(value = "/login" )
    public String login(String loginId,String loginPassword, HttpSession session) {
        Long userNo = userService.login(loginId, loginPassword);

        if(userNo == null){ // 로그인 실패
            return "login";
        }
        session.setAttribute("loginUser", userNo);
        return "redirect:/";
    }
    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "redirect:/";
    }
}
