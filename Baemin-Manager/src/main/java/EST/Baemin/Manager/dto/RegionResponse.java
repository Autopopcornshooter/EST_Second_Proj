package EST.Baemin.Manager.dto;

import EST.Baemin.Manager.domain.Region;
import lombok.Getter;

@Getter
public class RegionResponse {
  private Long id;
  private String address;
  private Double latitude;
  private Double longitude;
  private String google_place_id;
  private String apiKey;

  public RegionResponse(Region region) {
    this.id = region.getId();
    this.address = region.getAddress();
    this.latitude = region.getLatitude();
    this.longitude = region.getLongitude();
    this.google_place_id = region.getGoogle_place_id();
  }
}