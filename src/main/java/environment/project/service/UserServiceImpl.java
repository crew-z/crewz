package environment.project.service;

import environment.project.dto.UserDTO;
import environment.project.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserMapper userMapper;

    @Override
    public List<UserDTO> selectUser() {
        return userMapper.selectService();
    }

    @Override
    @Transactional
    public void insertUser(UserDTO userDTO){
        // To-do: Password to Hashcode
        userMapper.insertUser(userDTO);
    }

}