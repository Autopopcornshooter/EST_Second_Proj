package EST.Baemin.Manager.controller;

import EST.Baemin.Manager.dto.UserResponse;
import EST.Baemin.Manager.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String adminPage() {
        return "adminLoginPage";
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

    @GetMapping({"/users", "/users/{page}"})
    public String userManagement(@PathVariable(name = "page", required = false) Integer page,
                                 Model model) {

        // page 파라미터가 없으면 0페이지부터 시작
        int currentPage = (page != null) ? Math.max(0, page - 1) : 0;

        Pageable pageable = PageRequest.of(currentPage, 6, Sort.by("id").ascending());

        Page<UserResponse> userPage = userService.getAllUsers(pageable);

        model.addAttribute("userPage", userPage);
        model.addAttribute("userList", userPage.getContent());
        model.addAttribute("currentPage", currentPage + 1);
        model.addAttribute("isSearch", false);

        return "admin-users";
    }

    @GetMapping({"/users/search/{keyword}", "/users/search/{keyword}/{page}"})
    public String searchUsers(@PathVariable("keyword") String keyword,
                              @PathVariable(value = "page", required = false) Integer page,
                              Model model) {

        // page는 0부터 시작
        int currentPage = (page != null) ? Math.max(0, page - 1) : 0;

        Pageable pageable = PageRequest.of(currentPage, 6, Sort.by("id").ascending());

        Page<UserResponse> userPage = userService.searchUsersByName(keyword, pageable);

        model.addAttribute("userPage", userPage);
        model.addAttribute("userList", userPage.getContent());
        model.addAttribute("currentPage", currentPage + 1);
        model.addAttribute("keyword", keyword);
        model.addAttribute("isSearch", true);

        return "admin-users";
    }

    @PatchMapping("/users/status/{id}")
    public ResponseEntity<UserResponse> updateUserStatus(
            @PathVariable Long id) {

        UserResponse updatedUser = userService.updateUserStatus(id);
        return ResponseEntity.ok(updatedUser);
    }
}
