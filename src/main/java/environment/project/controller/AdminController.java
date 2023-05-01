package environment.project.controller;

import environment.project.dto.ClubApplyDTO;
import environment.project.service.ClubApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
public class AdminController {
    @Autowired
    private ClubApplyService clubApplyService;

    @GetMapping("/admin")
    public String admin(Model model){
        List<ClubApplyDTO> clubApplyList = clubApplyService.selectClubApply();
        model.addAttribute("list",clubApplyList);
        return "admin";
    }

    @PostMapping("/admin")
    public String updateClubApply(@RequestParam Integer clubApplyNo, @RequestParam String method, @RequestParam String refuseReason,Model model){
        ClubApplyDTO dto = new ClubApplyDTO();
        List<ClubApplyDTO> clubApplyList = clubApplyService.selectClubApply();
        model.addAttribute("list",clubApplyList);
        int result;
        log.info("can: {}", clubApplyNo);
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
            log.info("success: {}","update success");
            model.addAttribute("success","업데이트 성공");
        }else{
            log.info("fail: {}","update fail");
            model.addAttribute("fail","업데이트 실패");
        }

        return "redirect:admin";
    }

}
