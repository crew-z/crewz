package environment.project.controller;

import environment.project.dto.ClubApplyDTO;
import environment.project.dto.UserDTO;
import environment.project.service.ClubApplyService;
import environment.project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ClubApplyController {
    final ClubApplyService clubapplyService;
    final UserService userService;
    @GetMapping("/newclub" )
    public String clubApplyForm(HttpSession session, Model model) {
        Long id = (Long) session.getAttribute("loginUser");
        UserDTO user = userService.getUserByUserNo(id);
        log.info("user: {}",user);
        model.addAttribute("user",user);
        return "newclub";
    }

    @PostMapping("/newclub")
    public String apply(ClubApplyDTO clubApplyDTO, Model model){
        clubapplyService.insertClubApply(clubApplyDTO);
        Long clubApplyNo = clubApplyDTO.getClubApplyNo();
        ClubApplyDTO clubApply = clubapplyService.getApplicationByApplyNo(clubApplyNo);
        model.addAttribute("clubApply",clubApply);
        return "newclubview";
    }

    @PostMapping("/newclubresult")
    public String applyResult(ClubApplyDTO clubApplyDTO, Model model){
        ClubApplyDTO clubApply = clubapplyService.getApplicationByApplyNo(clubApplyDTO.getClubApplyNo());
        log.info("clubApply : {}", clubApply);
        model.addAttribute("clubApply",clubApply);
        return "newclubresult";
    }


}
