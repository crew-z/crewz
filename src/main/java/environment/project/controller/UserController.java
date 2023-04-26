package environment.project.controller;

import environment.project.dto.UserDTO;
import environment.project.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import environment.project.service.UserService;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//@RestController
@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;

    @ResponseBody
    @GetMapping("/userList")
    public List<UserDTO> selectUser(){
        return userMapper.selectService();
    }

}
