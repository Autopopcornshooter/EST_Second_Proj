package EST.Baemin.Manager.controller;

import EST.Baemin.Manager.dto.UserResponse;
import EST.Baemin.Manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserManagementController {
  private final UserService userService;

  @Autowired
  public UserManagementController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping({"/management/user", "/management/user/{page}"})
  public String userManagement(@PathVariable(name = "page", required = false) Integer page,
                               Model model) {

    // page 파라미터가 없으면 0페이지부터 시작
    int currentPage = (page != null) ? page - 1 : 0;

    Pageable pageable = PageRequest.of(currentPage, 5, Sort.by("id").ascending());

    Page<UserResponse> userPage = userService.getAllUsers(pageable);

    model.addAttribute("userPage", userPage);
    model.addAttribute("userList", userPage.getContent());
    model.addAttribute("currentPage", currentPage + 1);
    model.addAttribute("isSearch", false);

    return "userManagement";
  }

  @GetMapping("/management/user/search")
  public String searchUsers(@RequestParam("keyword") String keyword,
                           @RequestParam(value = "page", defaultValue = "0") int page,
                           Model model) {
    
    // page는 0부터 시작하므로 1을 빼줌
    int currentPage = Math.max(0, page - 1);
    
    Pageable pageable = PageRequest.of(currentPage, 5, Sort.by("id").ascending());
    
    Page<UserResponse> userPage = userService.searchUsersByName(keyword, pageable);
    
    model.addAttribute("userPage", userPage);
    model.addAttribute("userList", userPage.getContent());
    model.addAttribute("currentPage", currentPage + 1);
    model.addAttribute("keyword", keyword);
    model.addAttribute("isSearch", true);
    
    return "userManagement";
  }
}
