package environment.project.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import environment.project.dto.ClubInfoCreateDTO;
import environment.project.service.ClubInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/club-info")
@RequiredArgsConstructor
@Slf4j
public class ClubInfoController {
	private final ClubInfoService clubInfoService;
	private final HttpSession httpSession;

	// 유저 동아리 합류
	// POST: /club-info/{clubNo}
	@PostMapping("/{clubNo}")
	public ResponseEntity<Void> createClubInfo(@PathVariable Long clubNo,
		@RequestBody ClubInfoCreateDTO clubInfoCreateDTO) {
		Long userNo = (Long)httpSession.getAttribute("loginUser");
		clubInfoCreateDTO.setUserNo(userNo);

		clubInfoService.createClubInfo(clubInfoCreateDTO);
		return ResponseEntity.ok().build();
	}
}
