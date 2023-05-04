package environment.project.service;

import environment.project.dto.ClubInfoCreateDTO;
import environment.project.mapper.ClubInfoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClubInfoServiceImpl implements ClubInfoService {
    private final ClubInfoMapper clubInfoMapper;

    @Override
    public void createClubInfo(ClubInfoCreateDTO clubInfoCreateDTO) {
        clubInfoMapper.insertClubInfo(clubInfoCreateDTO);
    }

    @Override
    public boolean checkUserInClub(Long clubNo, Long userNo) {
        int count = clubInfoMapper.checkUserInClub(clubNo, userNo);
        return count > 0;
    }
}
