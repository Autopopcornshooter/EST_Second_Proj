package EST.Baemin.Manager.dto;

import lombok.Getter;

@Getter
public class UpdateRegionRequest {
  private String address;
  private double latitude;
  private double longitude;
  private String google_place_id;

  public UpdateRegionRequest(String address, double latitude, double longitude, String google_place_id) {
    this.address = address;
    this.latitude = latitude;
    this.longitude = longitude;
    this.google_place_id = google_place_id;
  }

}
