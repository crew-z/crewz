package environment.project.controller;

import environment.project.dto.UserDTO;
import environment.project.mapper.MypageMapper;
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

    @GetMapping(path = {  "/mypagemain" })
    public String loadMypageMain(Model model, HttpSession session) {
        Long userNo = (Long) session.getAttribute("loginUser");
        UserDTO userInfo = mypageService.selectUserInfoByUserNo(userNo);
        log.info("userInfo: {}",userInfo);

            List<HashMap<String, Object>> userJoinClub = mypageService.selectUserJoinClub(userNo,1);
            model.addAttribute("joinClub", userJoinClub);

            List<HashMap<String, Object>> userWaitingClub = mypageService.selectUserJoinClub(userNo, 0);
            model.addAttribute("waitclub", userWaitingClub);


        List<HashMap<String, Object>> clubLeader = mypageService.checkMemGrade(userNo);
        model.addAttribute("user", userInfo);
        model.addAttribute("clubLeader", clubLeader);
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
        return "redirect:mypagemain";
    }

    @GetMapping(path = {"/clubleaderpage"})
    public String getTest(){
        return "clubLeaderPage";
    }

    @PostMapping(path = {  "/clubleaderpage" })
    public String loadClubleaderPage(@RequestParam(required = false) Long clubNo,@RequestParam(defaultValue = "0",required = false) int clubUSerGrade, Model model, HttpSession session) {
        Long userNo = (Long) session.getAttribute("loginUser");

        if(clubUSerGrade == 1){
            List<HashMap<String, Object>> JoinClubMem = mypageService.selectClubApplicatedMemList(1, clubNo);
            model.addAttribute("clubInfo", JoinClubMem);
        } else if (clubUSerGrade == 0) {
            List<HashMap<String, Object>> applicateClubMem = mypageService.selectClubApplicatedMemList(0, clubNo);
            model.addAttribute("clubInfo", applicateClubMem);
        }
        List<HashMap<String, Object>> clubLeader = mypageService.checkMemGrade(userNo);
        model.addAttribute("clubLeader", clubLeader);
        return "clubLeaderPage";
    }


    @RequestMapping(value = "/applyMem", method = RequestMethod.POST)
    public String updateClubMem(Long userNo,Long clubNo, String method, RedirectAttributes redirectAttributes){
        int result;
        if(method.equals("ok")){
            result = mypageService.updateClubMemGrade(userNo,clubNo);
        } else if (method.equals("nok")) {
            result = mypageService.delteApplicatedUserInfo(userNo,clubNo);
        }
        List<HashMap<String, Object>> applicateClubMem = mypageService.selectClubApplicatedMemList(0, clubNo);
        redirectAttributes.addFlashAttribute("clubInfo", applicateClubMem);
        return "redirect:/clubleaderpage";
    }



}
