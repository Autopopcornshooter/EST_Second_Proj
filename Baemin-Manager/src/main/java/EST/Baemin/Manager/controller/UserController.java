package EST.Baemin.Manager.controller;

import EST.Baemin.Manager.dto.AddUserRequest;
import EST.Baemin.Manager.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class UserController {
    private final UserService userService;



    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/signup")
    public String signup(Model model) {

        model.addAttribute("formData",new AddUserRequest());
        return "signup";
    }


    @PostMapping("/signup")
    public String signup(AddUserRequest request, Model model){
        if(!request.getPassword().equals( request.getConfirmPassword())){
            model.addAttribute("formData",request);
            return"/signup";
        }
        userService.save(request);
        return "redirect:/login";
    }

    @PostMapping("/logout")
    public void logout(){
        throw new IllegalStateException("Spring Security 에서 자동으로 로그아웃 수행함");
    }



}
