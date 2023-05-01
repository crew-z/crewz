package environment.project.controller;

import environment.project.dto.UserDTO;
import environment.project.mapper.MypageMapper;
import environment.project.service.MypageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
//@Slf4j
@Controller
public class MypageController {

    @Autowired
    private MypageMapper mypageMapper;
    @Autowired
    private MypageService mypageService;

    @GetMapping(path = {  "/mypagemain" })
    public String loadMypageMain(Model model) {
//        List<UserDTO> userInfo = mypageMapper.selectUserInfoFromMypage(userId);
        UserDTO userInfo = mypageMapper.selectUserInfoFromMypage();
        List<HashMap<String, Object>> userClub = mypageMapper.selectUserClub();
        model.addAttribute("user", userInfo);
        model.addAttribute("club", userClub);
        return "mypageMain";
    }

    @PostMapping(path = {"/mypagemain"})
    public String modifyUserInfo(@RequestParam(required = false) String userNickname,@RequestParam(required = false) String userTel,@RequestParam(required = false) String userEmail, Model model){
        UserDTO userInfo = mypageMapper.selectUserInfoFromMypage();
        userInfo.setUserNickname(userNickname);
        userInfo.setUserEmail(userEmail);
        userInfo.setUserTel(userTel);
        mypageService.updateUserInfo(userInfo);
        model.addAttribute("user", userInfo);
        return "redirect:mypagemain";
    }

    @GetMapping(path = {  "/clubleaderpage" })
    public String loadClubleaderPage() {
//
        return "clubLeaderPage";
    }

}
