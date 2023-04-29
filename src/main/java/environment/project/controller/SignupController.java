package environment.project.controller;

import environment.project.dto.UserDTO;
import environment.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class SignupController {
    final UserService userService;
    @GetMapping(path = { "/signup" })
    public String signupForm() {
        return "signup";
    }
    @PostMapping("/signup")
    public String signup(UserDTO user){
        userService.insertUser(user);
        return "redirect:/login";
    }
}
