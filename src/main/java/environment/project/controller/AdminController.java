package environment.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import environment.project.dto.ClubApplyDTO;
import environment.project.dto.ClubDTO;
import environment.project.dto.ClubInfoDTO;
import environment.project.service.AdminService;
import environment.project.service.ClubApplyService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AdminController {

	@Autowired
	private ClubApplyService clubApplyService;
	@Autowired
	private AdminService adminService;

	@GetMapping("/admin")
	public String admin(@RequestParam(defaultValue = "all", required = false) String viewType, Model model) {
		List<ClubApplyDTO> clubApplyList = null;

		log.debug("viewType: {}", viewType);

		switch (viewType) {
			case "approve":
				clubApplyList = clubApplyService.selectClubApplyListToApproveYn("Y");
				break;
			case "refuse":
				clubApplyList = clubApplyService.selectClubApplyListToApproveYn("N");
				break;
			case "wait":
				clubApplyList = clubApplyService.selectClubApplyListToIsNull();
				break;
			default:
				clubApplyList = clubApplyService.selectClubApply();
				break;
		}

		model.addAttribute("list", clubApplyList);
		return "admin";
	}

	@PostMapping("/admin")
	public String updateClubApply(@RequestParam Long clubApplyNo, @RequestParam Long userNo,
		@RequestParam String method, @RequestParam String refuseReason, Model model) {
		ClubApplyDTO dto = new ClubApplyDTO();
		ClubDTO clubDTO = new ClubDTO();
		ClubInfoDTO clubInfoDTO = new ClubInfoDTO();
		List<ClubApplyDTO> clubApplyList = clubApplyService.selectClubApply();
		model.addAttribute("list", clubApplyList);
		int result;

		log.debug("method: {}", method);

		if (method.equals("approve")) {
			dto.setClubApplyNo(clubApplyNo);
			dto.setClubApproveYn("Y");
			dto.setClubRefuseReason("");
			result = clubApplyService.updateApproveYn(dto);
		} else {
			dto.setClubApplyNo(clubApplyNo);
			dto.setClubApproveYn("N");
			dto.setClubRefuseReason(refuseReason);
			result = clubApplyService.updateApproveYn(dto);
		}

		log.debug("result: {}", result);

		if (result != 0) {
			clubDTO.setClubApplyNo(clubApplyNo);
			adminService.createClub(clubDTO);
			clubInfoDTO.setClubNo(clubDTO.getClubNo());
			clubInfoDTO.setUserNo(userNo);
			clubInfoDTO.setClubUserGrade(2);
			adminService.createClubInfo(clubInfoDTO);
			model.addAttribute("success", "update success");
		} else {
			model.addAttribute("fail", "업데이트 실패");
		}

		return "redirect:admin";
	}

}
