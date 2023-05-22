package environment.project.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import environment.project.dto.UserDTO;
import environment.project.mapper.UserMapper;
import lombok.RequiredArgsConstructor;

//@RestController
@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserMapper userMapper;

	@ResponseBody
	@GetMapping("/userList")
	public List<UserDTO> selectUser() {
		return userMapper.selectService();
	}

}
