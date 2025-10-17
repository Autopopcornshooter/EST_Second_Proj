package EST.Baemin.Manager.dto;

import EST.Baemin.Manager.domain.Region;
import lombok.Getter;

@Getter
public class RegionResponse {
  private final Long id;
  private final String address;
  private final Double latitude;
  private final Double longitude;
  private final String google_place_id;

  public RegionResponse(Region region) {
    this.id = region.getId();
    this.address = region.getAddress();
    this.latitude = region.getLatitude();
    this.longitude = region.getLongitude();
    this.google_place_id = region.getGoogle_place_id();
  }
}