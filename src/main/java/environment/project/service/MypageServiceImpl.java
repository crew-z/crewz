package environment.project.service;

import environment.project.dto.ClubInfoDTO;
import environment.project.dto.UserDTO;
import environment.project.mapper.MypageMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
    public void updateUserInfo(UserDTO userDTO) {
        mypageMapper.updateUserInfo(userDTO);
    }

    @Override
    public List<HashMap<String, Object>> selectUserJoinClub(Long userNo) {

        return mypageMapper.selectUserClub(userNo);
    }

    @Override
    public UserDTO selectUserInfoByUserNo(Long userNo) {
        return mypageMapper.selectUserInfoByUserNo(userNo);
    }

    @Override
    public int updateClubMemGrade(Long userNo, Long clubNo){
        return mypageMapper.updateUserClubJoin(userNo, clubNo);
    }

    @Override
    public int delteApplicatedUserInfo(Long userNo, Long clubNo){
        return mypageMapper.delteApplicatedUserJoinClub(userNo,clubNo);
    }

}