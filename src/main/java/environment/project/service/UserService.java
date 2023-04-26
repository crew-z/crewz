package environment.project.service;


import environment.project.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> selectUser();
}
