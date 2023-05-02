package environment.project.service;

import environment.project.dto.ClubApplyDTO;
import environment.project.mapper.ClubApplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClubApplyServiceImpl implements ClubApplyService{

    private final ClubApplyMapper clubApplyMapper;

    @Override
    public List<ClubApplyDTO> selectClubApply() {
        return clubApplyMapper.selectService();
    }

    @Override
    public List<ClubApplyDTO> selectClubApplyListToApproveYn(String approveYn) {
        return clubApplyMapper.selectClubApplyListToApproveYn(approveYn);
    }

    @Override
    public int updateApproveYn(ClubApplyDTO clubApplyDTO) {
        return clubApplyMapper.updateService(clubApplyDTO);
    }
}
