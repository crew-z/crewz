package environment.project.service;

import environment.project.dto.ClubDTO;
import environment.project.dto.ClubInfoDTO;

public interface AdminService {
    int createClub(ClubDTO clubDTO);
    int createClubInfo(ClubInfoDTO clubInfoDTO);
}
