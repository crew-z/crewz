package environment.project.service;

import environment.project.dto.ClubApplyDTO;
import environment.project.dto.ClubNameDTO;
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
    public UserDTO selectUserInfoByUserNo(Long userNo) {
        return mypageMapper.selectUserInfoByUserNo(userNo);
    }

    @Override
    public void updateUserInfo(UserDTO userDTO) {
        mypageMapper.updateUserInfo(userDTO);
    }

    @Override
    public List<ClubApplyDTO> loadClubApproveResult(Long userNo) {
        return mypageMapper.selectClubApproveResult(userNo);
    }

    @Override
    public ClubNameDTO viewClubNameByClubNo(Long clubNo){
        return mypageMapper.selectClubName(clubNo);
    }
    @Override
    public List<HashMap<String, Object>> selectUserJoinClub(Long userNo, int clubUserGrade) {
        return mypageMapper.selectUserClub(userNo, clubUserGrade);
    }

    @Override
    public List<HashMap<String,Object>> selectClubApplicatedMemList(int clubUserGrade, Long clubNo) {
        return mypageMapper.selectClubApplicationMemInfo(clubUserGrade,clubNo);
    }

    @Override
    public List<HashMap<String, Object>> checkMemGrade(Long userNo){
        return mypageMapper.selectLeaderGradeByClubName(userNo);
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
