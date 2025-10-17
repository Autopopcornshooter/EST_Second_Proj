package EST.Baemin.Manager.dto;

import EST.Baemin.Manager.domain.Region;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RegionRequest {
  private Long id;
  private String address;
  private Double latitude;
  private Double longitude;
  private String google_place_id;

  public Region toEntity(){
    return Region.builder()
        .address(this.address)
        .latitude(this.latitude)
        .longitude(this.longitude)
        .google_place_id(this.google_place_id)
        .build();
  }
}
