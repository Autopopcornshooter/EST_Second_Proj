package EST.Baemin.Manager.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class S3Service {

  private final S3Client s3Client;
  private final String bucketName;
  private final String region;

  public S3Service(@Value("${aws.access-key-id}") String accessKey, @Value("${aws.secret-access-key}") String secretKey, @Value("${aws.region}") String region, @Value("${aws.s3.bucket-name}") String bucketName) {

    this.bucketName = bucketName;
    this.region = region;

    // 인증 정보를 담은 credentials 객체 생성
    AwsBasicCredentials credentials = AwsBasicCredentials.create(accessKey, secretKey);

    // AWS S3와 통신할 클라이언트 객체 생성
    this.s3Client = S3Client.builder().region(Region.of(region)).credentialsProvider(StaticCredentialsProvider.create(credentials)).build();
  }

  /**
   * 파일 업로드
   */
  public String uploadFile(MultipartFile file) throws IOException {
    String fileName = generateFileName(file.getOriginalFilename());

    PutObjectRequest request = PutObjectRequest.builder().bucket(bucketName).key(fileName).contentType(file.getContentType()).build();

    // S3에 파일 업로드 수행
    s3Client.putObject(request, RequestBody.fromBytes(file.getBytes()));

    return getFileUrl(fileName);
  }

  /**
   * 고유한 파일명 생성
   */
  private String generateFileName(String originalFilename) {
    return UUID.randomUUID().toString() + "-" + originalFilename;
  }

  /**
   * 파일 다운로드 URL 생성
   */
  public String getFileUrl(String fileName) {
    GetObjectRequest request = GetObjectRequest.builder().bucket(bucketName).key(fileName).build();

    // 공개 URL (버킷이 public일 경우)
    return String.format("https://%s.s3.%s.amazonaws.com/%s", bucketName, region, fileName);
  }

  /**
   * 파일 삭제
   */
  public void deleteFile(String fileName) {
    DeleteObjectRequest request = DeleteObjectRequest.builder().bucket(bucketName).key(fileName).build();

    s3Client.deleteObject(request);
  }

  /**
   * 버킷의 모든 파일 목록 조회
   */
  public List<String> listFiles() {
    ListObjectsV2Request request = ListObjectsV2Request.builder().bucket(bucketName).build();

    ListObjectsV2Response response = s3Client.listObjectsV2(request);

    return response.contents().stream().map(S3Object::key).toList();
  }
}