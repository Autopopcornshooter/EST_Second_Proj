package EST.Baemin.Manager.dto;

import lombok.Getter;

@Getter
public class UpdateRegionRequest {
  private final String address;
  private final Double latitude;
  private final Double longitude;
  private final String google_place_id;

  public UpdateRegionRequest(String address, Double latitude, Double longitude, String google_place_id) {
    this.address = address;
    this.latitude = latitude;
    this.longitude = longitude;
    this.google_place_id = google_place_id;
  }

}
