package EST.Baemin.Manager.dto;

import EST.Baemin.Manager.domain.Restaurant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantDto {
    private Long id;
    private String name;
    private String mainMenu;
    private String description;
    private String address;
    private Integer price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer view;
    private String imageUrl;
    private boolean state;
    private Long userId;

    public RestaurantDto(Restaurant restaurant) {
        this.id = restaurant.getId();
        this.name = restaurant.getName();
        this.mainMenu = restaurant.getMainMenu();
        this.description = restaurant.getDescription();
        this.address = restaurant.getAddress();
        this.price = restaurant.getPrice();
        this.createdAt = restaurant.getCreatedAt();
        this.updatedAt = restaurant.getUpdatedAt();
        this.view = restaurant.getView();
        this.imageUrl = restaurant.getImageUrl();
        this.userId = restaurant.getUser().getId();
        this.state = restaurant.isState();
    }
}
