package EST.Baemin.Manager.dto;

import EST.Baemin.Manager.domain.Region;
import lombok.Getter;

@Getter
public class RegionResponse {
  private long id;
  private String address;
  private double latitude;
  private double longitude;
  private String google_place_id;
  private String apiKey;

  public RegionResponse(Region region) {
    this.id = region.getId();
    this.address = region.getAddress();
    this.latitude = region.getLatitude();
    this.longitude = region.getLongitude();
    this.google_place_id = region.getGoogle_place_id();
  }

  public RegionResponse(Region region, String apiKey) {
    this(region); // 위 생성자 재사용
    this.apiKey = apiKey;
  }
}