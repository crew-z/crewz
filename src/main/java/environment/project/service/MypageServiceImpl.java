package environment.project.service;

import environment.project.dto.UserDTO;
import environment.project.mapper.MypageMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MypageServiceImpl implements MypageService{

    private final MypageMapper mypageMapper;


//    @Override
//    public List<UserDTO> selectUserInfoFromMypage(String userId) {
//        return mypageMapper.selectUserInfoFromMypage(userId);
//    }
    @Override
    public UserDTO selectUserInfoFromMypage() {
        return mypageMapper.selectUserInfoFromMypage();
    }

    @Override
    public void updateUserInfo(UserDTO userDTO) {
        mypageMapper.updateUserInfo(userDTO);
    }

    @Override
    public List<HashMap<String, Object>> selectUserJoinClub() {
        return mypageMapper.selectUserClub();
    }

}