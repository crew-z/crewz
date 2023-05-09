package environment.project.service;

import environment.project.dto.ClubInfoCreateDTO;

public interface ClubInfoService {
    void createClubInfo(ClubInfoCreateDTO clubInfoCreateDTO);
    boolean checkUserInClub(Long clubNo, Long userNo);
}

