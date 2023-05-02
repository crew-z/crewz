package environment.project.service;


import environment.project.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> selectUser();
    Long insertUser(UserDTO userDTO);
    Long login(String loginId, String loginPassword);
    UserDTO getUserByUserNo(Long id);
}
