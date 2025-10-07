package EST.Baemin.Manager.controller;

import EST.Baemin.Manager.domain.Restaurant;
import EST.Baemin.Manager.dto.RestaurantDto;
import EST.Baemin.Manager.service.RestaurantService;
import EST.Baemin.Manager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import java.util.List;

@Tag(name = "Restaurant", description = "식당 관리 API")
@Controller
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    // 식당 전체 조회 기능
    @Operation(summary = "식당 전체 조회", description = "전체 식당을 조회합니다.")
    @GetMapping
    public String showRestaurant(
            // 페이지 변호 0부터 시작
            @RequestParam(defaultValue = "0") int page,
            // 한 페이지에 16개 식당 보여줌
            @RequestParam(defaultValue = "16") int size,
            Model model) {
        // 페이지 번호와 페이지 크기를 기반으로 pageable 생성
        Pageable pageable = PageRequest.of(page, size);

        // 서비스에서 Page<RestaurantDto> 반환
        Page<RestaurantDto> restaurantPage = restaurantService.findAllRestaurants(pageable);

        // model에 restaurants라는 이름으로 Page 객체를 전달
        model.addAttribute("restaurants", restaurantPage);

//        List<RestaurantDto> reestaurants = restaurantService.findAllRestaurants();
//        model.addAttribute("restaurants", restaurantService.findAllRestaurants());
        return "introductionpage";
    }

    // 식당 아이디별 조회 기능
    @Operation(summary = "식당 아이디별 조회", description = "해당 아이디의 식당을 조회합니다.")
    @GetMapping("/{id}")
    public String showRestaurantDetail(@PathVariable Long id, Model model) {
        // 조회수 로직
        restaurantService.increaseView(id);

        // DB 조회하고 최신 데이터 가져오기
        RestaurantDto restaurant = restaurantService.findRestaurantById(id)
                .orElseThrow(() -> new RuntimeException("식당을 찾을 수 없습니다."));
        model.addAttribute("restaurant", restaurant);
        return "restaurantdetail";
    }

    // 식당 추가 기능
    @Operation(summary = "식당 등록", description = "새로운 식당을 추가합니다.")
    @PostMapping
    public ResponseEntity<RestaurantDto> createRestaurant(@RequestBody RestaurantDto dto) {
        return ResponseEntity.ok(restaurantService.createRestaurant(dto));
    }

    // 식당 수정 기능
    @Operation(summary = "식당 수정", description = "식당 정보를 수정합니다.")
    @PutMapping("/{id}")
    public ResponseEntity<RestaurantDto> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantDto dto) {
        return restaurantService.updateRestaurant(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 식당 삭제 기능
    @Operation(summary = "식당 삭제", description = "식당 정보를 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id) {
        restaurantService.deleteRestaurant(id);
        return ResponseEntity.ok().build();
    }

    // 식당 이미지 업로드 기능
//    @PostMapping("/{id}/image")
//    public ResponseEntity<Restaurant> uploadImage(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
//        try {
//            return restaurantService.uploadImage(id, file)
//                    .map(ResponseEntity::ok)
//                    .orELse(ResponseEntity.notFound().build());
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().build();
//        }
//    }

    // 식당 등록 폼 페이지 (새 레스토랑 등록)
    @Operation(summary = "식당 등록", description = "식당 정보를 등록합니다.")
    @GetMapping("/restaurant-form")
    public String restaurantForm() {
        return "restaurant-form";
        // templetes/restaurantapplicationpage.html 랜더링
    }

    // 식당 수정 페이지 (기존 레스토랑 수정)
        // 수정 페이지 접근
    @GetMapping("/{id}/edit")
    public String editRestaurant(@PathVariable Long id, Model model) {
        RestaurantDto restaurant = restaurantService.findRestaurantById(id)
                .orElseThrow(() -> new RuntimeException("식당을 찾을 수 없습니다."));
        model.addAttribute("restaurant", restaurant);   // 기존 데이터 폼에 채우기
        return "restaurant-form";
    }

        // 폼 제출 처리
    @PostMapping("/save")
    public String saveRestaurant(RestaurantDto dto) {
        if (dto.getId() == null) {
            throw new RuntimeException("수정할 식당 id가 없습니다.");
        }

        restaurantService.updateRestaurant(dto.getId(), dto)
                .orElseThrow(() -> new RuntimeException("해당 식당을 찾을 수 없습니다."));

        return "redirect:/api/restaurants";
    }

    // 식당 검색 기능
    @GetMapping("/search")
    public String search(@RequestParam("q") String keyword, Model model) {
        List<RestaurantDto> restaurants = restaurantService.searchRestaurant(keyword);
        model.addAttribute("results", restaurants);
        model.addAttribute("keyword", keyword);
        return "restaurant-search";
    }

}
