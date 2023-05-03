package environment.project.service;


import environment.project.dto.ClubApplyDTO;

import java.util.List;

public interface ClubApplyService {
    List<ClubApplyDTO> selectClubApply();

    int updateApproveYn(ClubApplyDTO clubApplyDTO);

    int insertClubApply(ClubApplyDTO clubApplyDTO);

    ClubApplyDTO getApplicationByApplyNo(Long clubApplyNo);
}
