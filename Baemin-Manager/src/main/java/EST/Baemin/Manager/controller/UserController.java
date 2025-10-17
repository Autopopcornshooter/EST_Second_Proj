package EST.Baemin.Manager.controller;

import EST.Baemin.Manager.dto.SignupRequest;
import EST.Baemin.Manager.service.UserService;
import EST.Baemin.Manager.util.SecurityUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;


    @GetMapping("/login")
    public String login() {
        if (SecurityUtil.isAuthenticated()) {
            return "redirect:/mainpage";
        }
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        if (SecurityUtil.isAuthenticated()) {
            return "redirect:/mainpage";
        }
        model.addAttribute("formData", new SignupRequest());
        return "signup";
    }

    @GetMapping("/storeName")
    public String storeName() {
        if(!userService.isStoreNameSet()){
            return "storeNamePage";
        }
        return "redirect:/api/restaurants";
    }


    @GetMapping("/access-denied")
    public String accessDenied(){
        return "accessDeniedPage";
    }


    @PostMapping("/api/storeName")
    public String setStoreName(@RequestParam String storeName) {
        userService.updateStoreName(storeName, SecurityUtil.getCurrentUserLoginId());
        log.info("상호명: "+storeName);
        return "RegionPage";
    }



    @GetMapping("/mainpage")
    public String toMainpage(Model model, Authentication authentication) {
        if(authentication !=null && authentication.isAuthenticated()){
            model.addAttribute("role",authentication.getAuthorities().iterator().next().getAuthority());
        }
        return "mainpage";
    }

    @PostMapping("/api/uploadUserIcon")
    public void uploadUserIcon(String url) {
        userService.addUserIcon(url);
    }

    @PostMapping("/api/defaultUserIcon")
    public void deleteUserIcon() {
        userService.deleteUserIcon();
    }

    @PostMapping("/api/signup")
    public String signup(SignupRequest request, Model model) {

        log.info(request.getUsername());
        if(userService.isLoginIdExist(request.getUsername())){
            model.addAttribute("formData", request);
            model.addAttribute("errorMessage", "이미 존재하는 아이디입니다.");
            return "signup";
        }
        if (!request.getPassword().equals(request.getConfirmPassword())) {
            model.addAttribute("formData", request);
            return "signup";
        }

        userService.save(request);
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public void logout() {
        throw new IllegalStateException("Spring Security 에서 자동으로 로그아웃 수행함");
    }



}
