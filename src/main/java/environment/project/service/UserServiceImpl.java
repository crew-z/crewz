package environment.project.service;

import environment.project.dto.UserDTO;
import environment.project.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserMapper userMapper;

    @Override
    public List<UserDTO> selectUser() {
        return userMapper.selectService();
    }
}