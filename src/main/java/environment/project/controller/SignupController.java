package environment.project.controller;

import environment.project.dto.UserDTO;
import environment.project.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class SignupController {
    final UserService userService;
    @GetMapping(path = { "/signup" })
    public String signupForm() {
        return "signup";
    }
    @PostMapping("/signup")
    public String signup(@Valid UserDTO user, Errors errors, Model model, HttpSession session){
        if (errors.hasErrors()){
            model.addAttribute("userDTO", user);
            Map<String, String> validateMap = new HashMap<>();

            for (FieldError error : errors.getFieldErrors()) {
                String validKeyName = "valid_" + error.getField();
                validateMap.put(validKeyName, error.getDefaultMessage());
            }

            for (String key : validateMap.keySet()) {
                model.addAttribute(key, validateMap.get(key));
            }
            return "signup";
        }

        // 회원가입 성공시
        Long id = userService.insertUser(user);

        session.setAttribute("loginUser", id);
        session.setAttribute("name",user.getUserName());

        return "signupSuccess";
    }
}