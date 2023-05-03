package environment.project.controller;

import environment.project.dto.ClubApplyDTO;
import environment.project.dto.ClubDTO;
import environment.project.dto.ClubInfoDTO;
import environment.project.service.AdminService;
import environment.project.service.ClubApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class AdminController {

    @Autowired
    private ClubApplyService clubApplyService;
    @Autowired
    private AdminService adminService;

    @GetMapping("/admin")
    public String admin(@RequestParam(defaultValue = "all",required = false) String viewType,Model model){
        List<ClubApplyDTO> clubApplyList = null;

        if(viewType.equals("approve")){
            clubApplyList = clubApplyService.selectClubApplyListToApproveYn("Y");
        }else if(viewType.equals("refuse")){
            clubApplyList = clubApplyService.selectClubApplyListToApproveYn("N");
        }else if(viewType.equals("wait")){
            clubApplyList = clubApplyService.selectClubApplyListToIsNull();
        }else {
            clubApplyList = clubApplyService.selectClubApply();
        }
        log.info("clubApplyList: {}",clubApplyList);
        model.addAttribute("list",clubApplyList);
        return "admin";
    }


    @PostMapping("/admin")
    public String updateClubApply(@RequestParam Long clubApplyNo,@RequestParam Long userNo, @RequestParam String method, @RequestParam String refuseReason,Model model){
        ClubApplyDTO dto = new ClubApplyDTO();
        ClubDTO clubDTO = new ClubDTO();
        ClubInfoDTO clubInfoDTO = new ClubInfoDTO();
        List<ClubApplyDTO> clubApplyList = clubApplyService.selectClubApply();
        model.addAttribute("list",clubApplyList);
        int result;
        log.info("can: {}", clubApplyNo);
        log.info("userNo: {}", userNo);
        log.info("method: {}", method);
        log.info("refuse_reason: {}",refuseReason);
        if(method.equals("approve")){
            dto.setClubApplyNo(clubApplyNo);
            dto.setClubApproveYn("Y");
            dto.setClubRefuseReason("");
            result = clubApplyService.updateApproveYn(dto);
        }else{
            dto.setClubApplyNo(clubApplyNo);
            dto.setClubApproveYn("N");
            dto.setClubRefuseReason(refuseReason);
            result = clubApplyService.updateApproveYn(dto);
        }

        if(result != 0){
            //동아리 생성
            clubDTO.setClubApplyNo(clubApplyNo);
            adminService.createClub(clubDTO);
            //최초의 동아리 장 생성
            clubInfoDTO.setClubNo(clubDTO.getClubNo());
            clubInfoDTO.setUserNo(userNo);
            //동아리 원 : 1 , 동아리 장 : 2
            clubInfoDTO.setClubUserGrade(2);
            adminService.createClubInfo(clubInfoDTO);
            log.info("success: {}","update success");
            model.addAttribute("success","update success");
        }else{
            log.info("fail: {}","update fail");
            model.addAttribute("fail","업데이트 실패");
        }

        return "redirect:admin";
    }


}
