package EST.Baemin.Manager.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "photos")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@AllArgsConstructor
public class Photo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String s3Key;  // S3에서의 파일 키

  @Column(nullable = false)
  private String s3Url;  // S3 파일 URL

  @Column(nullable = false)
  private String originalFilename;

  @Column(nullable = false)
  private LocalDateTime uploadDate;

}