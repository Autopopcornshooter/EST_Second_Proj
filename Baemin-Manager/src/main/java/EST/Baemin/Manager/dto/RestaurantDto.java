package EST.Baemin.Manager.dto;

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
    private String name;
    private String mainMenu;
    private String description;
    private String address;
    private Integer price;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
//    private String imageUrl;
}
