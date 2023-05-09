package environment.project.controller;

import environment.project.dto.ClubApplyDTO;
import environment.project.dto.ClubNameDTO;
import environment.project.dto.UserDTO;
import environment.project.service.ClubApplyService;
import environment.project.service.MypageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
@Slf4j
@Controller
public class MypageController {

    @Autowired
    private MypageService mypageService;
    @Autowired
    private ClubApplyService clubApplyService;

    @GetMapping(path = {  "/mypagemain" })
    public String loadMypageMain(Model model, HttpSession session) {
        Long userNo = (Long) session.getAttribute("loginUser");
        UserDTO userInfo = mypageService.selectUserInfoByUserNo(userNo);
        List<HashMap<String, Object>> userJoinClub = mypageService.selectUserJoinClub(userNo,1);
        List<HashMap<String, Object>> userWaitingClub = mypageService.selectUserJoinClub(userNo, 0);
        List<HashMap<String, Object>> clubLeader = mypageService.checkMemGrade(userNo);
        List<ClubApplyDTO> clubResult = mypageService.loadClubApproveResult(userNo);
        model.addAttribute("user", userInfo);
        model.addAttribute("clubLeader", clubLeader);
        model.addAttribute("waitclub", userWaitingClub);
        model.addAttribute("joinClub", userJoinClub);
        model.addAttribute("clubResult", clubResult);
        return "mypageMain";
    }

    @PostMapping(path = {"/mypagemain"})
    public String modifyUserInfo(@RequestParam(required = false) String userNickname,@RequestParam(required = false) String userTel,@RequestParam(required = false) String userEmail, HttpSession session, Model model){
        Long userNo = (Long) session.getAttribute("loginUser");
        UserDTO userInfo = mypageService.selectUserInfoByUserNo(userNo);
        userInfo.setUserNickname(userNickname);
        userInfo.setUserEmail(userEmail);
        userInfo.setUserTel(userTel);
        mypageService.updateUserInfo(userInfo);
        model.addAttribute("user", userInfo);
        return "redirect:/mypagemain";
    }
    @PostMapping(path = {"result"})
    public String newClubResult(@RequestParam("clubApplyNo") Long clubApplyNo, Model model) {
        ClubApplyDTO clubApply = clubApplyService.getApplicationByApplyNo(clubApplyNo);
        model.addAttribute("clubApply", clubApply);
        return "newclubresult";
    }

    @GetMapping(path = {"/clubleaderpage"})
    public String getTest(){
        return "clubLeaderPage";
    }

    @PostMapping(path = {  "/clubleaderpage" })
    public String loadClubleaderPage(@RequestParam(required = false) Long clubNo, Model model, HttpSession session) {
        Long userNo = (Long) session.getAttribute("loginUser");
        List<HashMap<String, Object>> applicateClubMem = mypageService.selectClubApplicatedMemList(0, clubNo);
        List<HashMap<String, Object>> clubLeader = mypageService.checkMemGrade(userNo);
        ClubNameDTO clubName = mypageService.viewClubNameByClubNo(clubNo);
        model.addAttribute("postClubNo", clubNo);
        model.addAttribute("clubLeader", clubLeader);
        model.addAttribute("clubInfo", applicateClubMem);
        model.addAttribute("clubName", clubName);
        return "clubLeaderPage";
    }

    @RequestMapping(value = "/applyMem", method = RequestMethod.POST)
    public String updateClubMem(Long userNo, Long clubNo, String method, RedirectAttributes redirectAttributes){

        if(method.equals("ok")){
            mypageService.updateClubMemGrade(userNo,clubNo);
        } else if (method.equals("nok")) {
            mypageService.delteApplicatedUserInfo(userNo,clubNo);
        }

        List<HashMap<String, Object>> applicateClubMem = mypageService.selectClubApplicatedMemList(0, clubNo);
        ClubNameDTO clubName = mypageService.viewClubNameByClubNo(clubNo);
        redirectAttributes.addFlashAttribute("clubInfo", applicateClubMem);
        redirectAttributes.addFlashAttribute("clubName", clubName);
        return "redirect:/clubleaderpage";
    }



}
