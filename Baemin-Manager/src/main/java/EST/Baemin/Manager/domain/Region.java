package EST.Baemin.Manager.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Region {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column
  private String address;

  @Column
  private Double latitude;

  @Column
  private Double longitude;

  @Column
  private String google_place_id;

  @Builder
  public Region(String address, Double latitude, Double longitude, String google_place_id) {
    this.address = address;
    this.latitude = latitude;
    this.longitude = longitude;
    this.google_place_id = google_place_id;
  }

  public void update(String address, Double latitude, Double longitude, String google_place_id){
    this.address = address;
    this.latitude = latitude;
    this.longitude = longitude;
    this.google_place_id = google_place_id;
  }
}
