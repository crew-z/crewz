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
import org.springframework.web.servlet.ModelAndView;

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
        log.info("id: {}",id);
        log.info("user: {}",user);
        model.addAttribute("name",user.getUserName());

        return "newclub";
    }

    @PostMapping("/newclub")
    public ModelAndView apply(ClubApplyDTO clubApplyDTO, Model model){
        Long id = clubapplyService.insertClubApply(clubApplyDTO);
        ModelAndView mav = new ModelAndView();
        mav.setViewName("/newclub/view");
        model.addAttribute("clubApplyNo",id);
        return mav;
    }
    @GetMapping("/newclub/view")
    public String applyView(Model model){
        Long clubApplyNo = (Long) model.getAttribute("clubApplyNo");
        clubapplyService.getApplicationByApplyNo(clubApplyNo);
        return "newclub/view";
    }



}
