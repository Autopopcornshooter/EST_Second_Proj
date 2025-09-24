package EST.Baemin.Manager.repository;

import EST.Baemin.Manager.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
}
