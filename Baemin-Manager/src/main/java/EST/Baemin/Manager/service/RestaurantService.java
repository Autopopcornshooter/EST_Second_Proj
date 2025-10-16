package EST.Baemin.Manager.service;

import EST.Baemin.Manager.domain.Restaurant;
import EST.Baemin.Manager.domain.User;
import EST.Baemin.Manager.dto.RestaurantDto;
import EST.Baemin.Manager.repository.RestaurantRepository;
import EST.Baemin.Manager.repository.UserRepository;
import EST.Baemin.Manager.util.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    // 식당 조회 기능
    public Page<RestaurantDto> findAllRestaurants(Pageable pageable) {

        return restaurantRepository.findAll(pageable)
                .map(RestaurantDto::new);    // DTO 변환 예시


//        return restaurantRepository.findAll()
//                .stream()
//                .map(RestaurantDto::new)
//                .toList();
    }

    // 식당 아이디별 조회 기능
    public Optional<RestaurantDto> findRestaurantById(Long id) {

        return restaurantRepository.findById(id)
                .map(RestaurantDto::new);
    }

    // 식당 추가 기능
    // feat: 식당에 user 필드 추가, user에 식당 리스트 필드 추가로 인한 수정
    @Transactional
    public RestaurantDto createRestaurant(RestaurantDto dto) {
        User user = userRepository.findByLoginId(SecurityUtil.getCurrentUserLoginId()).orElseThrow(() -> new IllegalArgumentException("findById Not Found with id : " + SecurityUtil.getCurrentUserLoginId()));

        Restaurant restaurant = Restaurant.builder()
                .name(dto.getName() != null && !dto.getName().isEmpty() ? dto.getName() : user.getStoreName())
                .mainMenu(dto.getMainMenu())
                .description(dto.getDescription())
                .address(dto.getAddress())
                .price(dto.getPrice())
                .view(dto.getView() != null ? dto.getView() : 0)    // null 값이면 0으로
                .user(user)
                .state(dto.getState())
//                .imageUrl(dto.getImageUrl())
                .build();
        user.updateRestaurant(restaurant);
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
                    if (dto.getState() != null) r.setState(dto.getState());
//                  r.setImageUrl(dto.getImageUrl());
                    return restaurantRepository.save(r);
                })
                .map(RestaurantDto::new);
    }

    // 식당 삭제 기능
    public void deleteRestaurant(Long id) {
        restaurantRepository.findById(id).ifPresent(restaurant -> {
            User user = restaurant.getUser();
            if (user != null) {
                user.setRestaurant(null);   // User와의 연관 끊기
            }
            restaurant.setUser(null);   // Restaurant에서 User 연관 끊기
            restaurantRepository.delete(restaurant);
        });
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

    // 조회수 증가 기능
    @Transactional
    public void increaseView(Long id) {
        restaurantRepository.findById(id).ifPresent(restaurant -> {
            restaurant.setView(restaurant.getView() + 1);
            restaurantRepository.save(restaurant);
        });
    }

    // 검색 기능 추가
    public List<RestaurantDto> searchRestaurant(String keyword) {
        return restaurantRepository
                .findByNameContainingIgnoreCaseOrMainMenuContainingIgnoreCase(keyword, keyword)
                .stream()
                .map(RestaurantDto::new)
                .toList();
    }

    // address 기반 주소 추출
    public Page<RestaurantDto> findRestaurantsByCity(String city, Pageable pageable) {
      return restaurantRepository.findByAddressContaining(city, pageable)
          .map(RestaurantDto::new);
    }

    // 권한 체크 메서드
    public boolean isOwner(Long restaurantId, Long userId) {
        return restaurantRepository.findById(restaurantId)
                .map(r -> r.getUser().getId().equals(userId))
                .orElse(false);
    }

    public Page<RestaurantDto> searchRestaurantsByName(String keyword, Pageable pageable) {
      return restaurantRepository.findByNameContainingIgnoreCase(keyword, pageable)
          .map(RestaurantDto::new);
    }

}
