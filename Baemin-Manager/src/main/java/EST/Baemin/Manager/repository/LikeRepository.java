package EST.Baemin.Manager.repository;

import EST.Baemin.Manager.domain.Like;
import EST.Baemin.Manager.domain.Restaurant;
import EST.Baemin.Manager.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    Optional<Like> findByUserAndRestaurant(User user, Restaurant restaurant);
    int countByRestaurant(Restaurant restaurant);
    void deleteByUserAndRestaurant(User user, Restaurant restaurant);
}
