package EST.Baemin.Manager.repository;

import EST.Baemin.Manager.domain.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByNameContainingIgnoreCaseOrMainMenuContainingIgnoreCase(String name, String mainMenu);
}
