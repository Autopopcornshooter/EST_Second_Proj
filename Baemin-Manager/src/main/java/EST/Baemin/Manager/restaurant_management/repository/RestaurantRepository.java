package EST.Baemin.Manager.restaurant_management.repository;

import EST.Baemin.Manager.restaurant_management.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
