package EST.Baemin.Manager.controller;

import EST.Baemin.Manager.domain.Restaurant;
import EST.Baemin.Manager.dto.RestaurantDto;
import EST.Baemin.Manager.service.RestaurantService;
import EST.Baemin.Manager.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String showRestaurant(Model model) {
        List<RestaurantDto> reestaurants = restaurantService.findAllRestaurants();
        model.addAttribute("restaurants", restaurantService.findAllRestaurants());
        return "introductionpage";
    }

    // 식당 아이디별 조회 기능
    @Operation(summary = "식당 아이디별 조회", description = "해당 아이디의 식당을 조회합니다.")
    @GetMapping("/{id}")
    public String showRestaurantDetail(@PathVariable Long id, Model model) {
        restaurantService.findRestaurantById(id).ifPresent(dto -> model.addAttribute("restaurant", dto));
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
}
