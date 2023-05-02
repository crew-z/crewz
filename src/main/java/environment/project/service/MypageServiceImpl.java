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

    @Override
    public UserDTO selectUserInfoFromMypage(String userId) {
        return mypageMapper.selectUserInfoFromMypage(userId);
    }

    @Override
    public void updateUserInfo(UserDTO userDTO, String userId) {
        mypageMapper.updateUserInfo(userDTO, userId);
    }

    @Override
    public List<HashMap<String, Object>> selectUserJoinClub(String userId) {

        return mypageMapper.selectUserClub(userId);
    }

}