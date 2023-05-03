package environment.project.service;

import environment.project.dto.ClubDTO;
import environment.project.dto.ClubInfoDTO;
import environment.project.mapper.AdminMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService{

    private final AdminMapper adminMapper;

    @Override
    public int createClub(ClubDTO clubDTO) {
        return adminMapper.createClub(clubDTO);
    }

    @Override
    public int createClubInfo(ClubInfoDTO clubInfoDTO) {
        return adminMapper.createClubInfo(clubInfoDTO);
    }
}
