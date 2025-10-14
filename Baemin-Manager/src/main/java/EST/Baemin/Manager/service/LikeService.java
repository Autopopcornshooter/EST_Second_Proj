package EST.Baemin.Manager.service;

import EST.Baemin.Manager.domain.Like;
import EST.Baemin.Manager.domain.User;
import EST.Baemin.Manager.domain.Restaurant;
import EST.Baemin.Manager.repository.LikeRepository;
import EST.Baemin.Manager.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LikeService {
    private final LikeRepository likeRepository;
    private final RestaurantRepository restaurantRepository;

    // 좋아요 토글 기능
    public boolean toggleLike(User user, Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("식당을 찾을 수 없습니다."));

        Optional<Like> existing =  likeRepository.findByUserAndRestaurant(user, restaurant);

        if (existing.isPresent()) {
            // 이미 누른상태이면 삭제
            likeRepository.delete(existing.get());
            return false; // 좋아요 취소
        } else  {
            // 처음 누르면 저장
            Like like = new Like();
            like.setUser(user);
            like.setRestaurant(restaurant);
            likeRepository.save(like);
            return true; // 좋아요 추가됨
        }
    }

    public int getLikeCount(Long restaurantId) {
        Restaurant restaurant = restaurantRepository.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("식당을 찾을 수 없습니다."));
        return  likeRepository.countByRestaurant(restaurant);
    }

}
