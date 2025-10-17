package EST.Baemin.Manager.service;

import EST.Baemin.Manager.domain.Photo;
import EST.Baemin.Manager.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PhotoService {

  private final S3Service s3Service;
  private final PhotoRepository photoRepository;

  public Photo uploadPhoto(String title, MultipartFile file) throws IOException {
    // S3에 파일 업로드
    String s3Url = s3Service.uploadFile(file);
    String s3Key = extractKeyFromUrl(s3Url);

    // 데이터베이스에 메타데이터 저장
    Photo photo = Photo.builder()
                          .title(title)
                          .s3Key(s3Key)
                          .s3Url(s3Url)
                          .originalFilename(file.getOriginalFilename())
                          .uploadDate(LocalDateTime.now())
                          .build();
    return photoRepository.save(photo);
  }

  private String extractKeyFromUrl(String url) {
    // URL에서 S3 키 추출
    return url.substring(url.lastIndexOf("/") + 1);
  }

  public List<Photo> getAllPhotos() {
    return photoRepository.findAll();
  }

  public Optional<Photo> getPhoto(Long id) {
    return photoRepository.findById(id);
  }

  public void deletePhoto(Long id) throws Exception {
    Photo photo = photoRepository.findById(id)
                          .orElseThrow(() -> new Exception("사진을 찾을 수 없습니다."));

    // S3에서 파일 삭제
    s3Service.deleteFile(photo.getS3Key());
    // 데이터베이스에서 레코드 삭제
    photoRepository.deleteById(id);
  }
}