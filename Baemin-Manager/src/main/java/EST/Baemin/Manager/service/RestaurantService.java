package EST.Baemin.Manager.service;

import EST.Baemin.Manager.domain.Restaurant;
import EST.Baemin.Manager.dto.RestaurantDto;
import EST.Baemin.Manager.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    // 식당 조회 기능
    public List<RestaurantDto> findAllRestaurants() {

        return restaurantRepository.findAll()
                .stream()
                .map(RestaurantDto::new)
                .toList();
    }

    // 식당 아이디별 조회 기능
    public Optional<RestaurantDto> findRestaurantById(Long id) {

        return restaurantRepository.findById(id)
                .map(RestaurantDto::new);
    }

    // 식당 추가 기능
    public RestaurantDto createRestaurant(RestaurantDto dto) {
        Restaurant restaurant = Restaurant.builder()
                .name(dto.getName())
                .mainMenu(dto.getMainMenu())
                .description(dto.getDescription())
                .address(dto.getAddress())
                .price(dto.getPrice())
                .view(dto.getView())
//                .imageUrl(dto.getImageUrl())
                .build();

        Restaurant saved = restaurantRepository.save(restaurant);

        return new RestaurantDto(saved);
    }

    // 식당 수정 기능
    public Optional<RestaurantDto> updateRestaurant(Long id, RestaurantDto dto) {
        return restaurantRepository.findById(id)
                .map(r -> {
            // PutMapping 할 때 들어온값이 null이면 기존 데이터 유지
                    if (dto.getName() != null) r.setName(dto.getName());
                    if (dto.getMainMenu() != null) r.setMainMenu(dto.getMainMenu());
                    if (dto.getDescription() != null) r.setDescription(dto.getDescription());
                    if (dto.getAddress() != null) r.setAddress(dto.getAddress());
                    if (dto.getPrice() != null) r.setPrice(dto.getPrice());
//                  r.setImageUrl(dto.getImageUrl());
                 return restaurantRepository.save(r);
        })
                .map(RestaurantDto::new);
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
