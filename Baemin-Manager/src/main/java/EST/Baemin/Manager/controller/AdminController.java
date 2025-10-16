package EST.Baemin.Manager.controller;

import EST.Baemin.Manager.domain.Restaurant;
import EST.Baemin.Manager.dto.RestaurantDto;
import EST.Baemin.Manager.dto.UserResponse;
import EST.Baemin.Manager.repository.RestaurantRepository;
import EST.Baemin.Manager.service.RestaurantService;
import EST.Baemin.Manager.service.UserService;
import EST.Baemin.Manager.util.SecurityUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final RestaurantService restaurantService;
    private final RestaurantRepository restaurantRepository;

    @Autowired
    public AdminController(UserService userService, RestaurantService restaurantService,
        RestaurantRepository restaurantRepository) {
        this.userService = userService;
      this.restaurantService = restaurantService;
      this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/login")
    public String adminPage(HttpServletRequest request) {
        if(SecurityUtil.isAuthenticated()){
            return "redirect:/admin/users";
        }
        return "adminLoginPage";
    }

    @GetMapping("/statistics")
    public String statisticsPage(HttpServletRequest request, Model model) {
        model.addAttribute("currentPath", request.getRequestURI());
        return "admin-statistics";
    }
    @PostMapping("/admin/logout")
    public void adminLogout() {
        throw new IllegalStateException("Spring Security 에서 자동으로 로그아웃 수행함");
    }


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

  @GetMapping({"/articles", "/articles/{page}"})
  public String articlesManagement(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "6") int size,
      Model model) {

    Pageable pageable = PageRequest.of(page, size);
    Page<RestaurantDto> restaurantPage = restaurantService.findAllRestaurants(pageable);
    model.addAttribute("restaurants", restaurantPage);
    model.addAttribute("keyword", "");
    model.addAttribute("isSearch", false);
    return "admin-articles";
  }

  @PostMapping("/articles/{id}/toggle")
  @Transactional
  public String updateArticlesStatus(@PathVariable Long id) {
    Restaurant restaurant = restaurantRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 레스토랑 id: " + id));

    restaurant.setState("공개".equals(restaurant.getState()) ? "비공개" : "공개");

    return "redirect:/admin/articles";
  }

  @GetMapping({"/articles/search/{keyword}","/articles/search/{keyword}/{page}"})
  public String searchArticles(
      @PathVariable String keyword,
      @PathVariable(required = false) Integer page,
      @RequestParam(defaultValue = "6") int size,
      Model model) {

    Pageable pageable = PageRequest.of((page != null ? page - 1 : 0), size);
    Page<RestaurantDto> restaurantPage = restaurantService.searchRestaurantsByName(keyword, pageable);

    if (restaurantPage.isEmpty()) {
      return "redirect:/admin/articles";
    }

    model.addAttribute("restaurants", restaurantPage);
    model.addAttribute("keyword", keyword);
    model.addAttribute("isSearch", true);
    model.addAttribute("currentPage", (page != null ? page : 1));
    return "admin-articles";
  }

}
