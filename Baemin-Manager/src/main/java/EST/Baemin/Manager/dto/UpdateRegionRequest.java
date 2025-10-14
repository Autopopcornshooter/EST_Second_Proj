package EST.Baemin.Manager.dto;

import lombok.Getter;

@Getter
public class UpdateRegionRequest {
  private String address;
  private Double latitude;
  private Double longitude;
  private String google_place_id;

  public UpdateRegionRequest(String address, Double latitude, Double longitude, String google_place_id) {
    this.address = address;
    this.latitude = latitude;
    this.longitude = longitude;
    this.google_place_id = google_place_id;
  }

}
