package EST.Baemin.Manager.controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/login")
    public String adminPage() {
        return "adminLoginPage";
    }

    @GetMapping("/users")
    public String userPage(HttpServletRequest request, Model model) {
        model.addAttribute("currentPath", request.getRequestURI());
        return "admin-users";

    }

    @GetMapping("/articles")
    public String articlesPage(HttpServletRequest request,Model model) {
        model.addAttribute("currentPath", request.getRequestURI());
        return "admin-articles";
    }

    @GetMapping("/statistics")
    public String statisticsPage(HttpServletRequest request, Model model) {
        model.addAttribute("currentPath", request.getRequestURI());
        return "admin-statistics";
    }

//    @GetMapping("/sidebar")
//    public String sidebarPage(){
//        return "admin-sidebar";
//    }
}
