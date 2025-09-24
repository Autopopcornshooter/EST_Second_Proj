package EST.Baemin.Manager.restaurant_management.controller;

import EST.Baemin.Manager.restaurant_management.domain.Restaurant;
import EST.Baemin.Manager.restaurant_management.dto.RestaurantDto;
import EST.Baemin.Manager.restaurant_management.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    // 식당 전체 조회 기능
    @GetMapping
    public List<Restaurant> findAllRestaurants() {
        return restaurantService.findAllRestaurants();
    }

    // 식당 아이디별 조회 기능
    @GetMapping("/{id}")
    public ResponseEntity<Restaurant> findRestaurantById(@PathVariable Long id) {
        return restaurantService.findRestaurantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 식당 추가 기능
    @PostMapping
    public ResponseEntity<Restaurant> createRestaurant(@RequestBody RestaurantDto dto) {
        return ResponseEntity.ok(restaurantService.createRestaurant(dto));
    }

    // 식당 수정 기능
    @PutMapping("/{id}")
    public ResponseEntity<Restaurant> updateRestaurant(@PathVariable Long id, @RequestBody RestaurantDto dto) {
        return restaurantService.updateRestaurant(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 식당 삭제 기능
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
