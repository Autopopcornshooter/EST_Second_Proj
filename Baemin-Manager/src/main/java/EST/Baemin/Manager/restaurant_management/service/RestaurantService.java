package EST.Baemin.Manager.restaurant_management.service;

import EST.Baemin.Manager.restaurant_management.domain.Restaurant;
import EST.Baemin.Manager.restaurant_management.dto.RestaurantDto;
import EST.Baemin.Manager.restaurant_management.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    // 식당 조회 기능
    public List<Restaurant> findAllRestaurants() {
        return restaurantRepository.findAll();
    }

    // 식당 아이디별 조회 기능
    public Optional<Restaurant> findRestaurantById(Long id) {
        return restaurantRepository.findById(id);
    }

    // 식당 추가 기능
    public Restaurant createRestaurant(RestaurantDto dto) {
        Restaurant restaurant = Restaurant.builder()
                .name(dto.getName())
                .mainMenu(dto.getMainMenu())
                .description(dto.getDescription())
                .address(dto.getAddress())
                .price(dto.getPrice())
//                .imageUrl(dto.getImageUrl())
                .build();

        return restaurantRepository.save(restaurant);
    }

    // 식당 수정 기능
    public Optional<Restaurant> updateRestaurant(Long id, RestaurantDto dto) {
        return restaurantRepository.findById(id).map(r -> {
            r.setName(dto.getName());
            r.setMainMenu(dto.getMainMenu());
            r.setDescription(dto.getDescription());
            r.setAddress(dto.getAddress());
            r.setPrice(dto.getPrice());
//            r.setImageUrl(dto.getImageUrl());
            return restaurantRepository.save(r);
        });
    }

    // 식당 삭제 기능
    public void deleteRestaurant(Long id) {
        restaurantRepository.deleteById(id);
    }

//    // 이미지 업로드 기능
//    public Optional<Restaurant> uploadImage(Long id, MultipartFile file) {
//        return restaurantRepository.findById(id).map(r -> {
//            try {
//                String fileName = file.getOriginalFilename();
//                r.setImageUrl("/images/" + fileName);
//            } catch (Exception e) {
//                throw new RuntimeException("이미지 업로드 실패");
//            }
//            return restaurantRepository.save(r);
//        });
//    }
}
