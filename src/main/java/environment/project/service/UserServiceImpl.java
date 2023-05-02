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
    public Long insertUser(UserDTO userDTO){
        // To-do: Password to Hashcode
        return userMapper.insertUser(userDTO);
    }
  
    @Override
    public Long login(String loginId, String loginPassword){
        UserDTO userDTO = userMapper.getUserByLoginId(loginId);
        if(userDTO.getUserPassword().equals(loginPassword))
            return userDTO.getUserNo();
        return null;
    }

    @Override
    public UserDTO getUserByUserNo(Long id){
        return userMapper.getUserByUserNo(id);
    }

}