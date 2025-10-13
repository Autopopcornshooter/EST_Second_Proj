package EST.Baemin.Manager.service;

import EST.Baemin.Manager.domain.Region;
import EST.Baemin.Manager.dto.SignupRequest;
import EST.Baemin.Manager.domain.User;
import EST.Baemin.Manager.dto.RegionRequest;
import EST.Baemin.Manager.repository.UserRepository;
import EST.Baemin.Manager.util.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final RegionService regionService;
    private PasswordEncoder encoder;

    public User save(SignupRequest request){
        return userRepository.save(
                User.builder()
                        .loginId(request.getUsername())
                        .password(encoder.encode(request.getPassword()))
                        .nickname(request.getNickname())
                        .storeName(request.getStoreName())
                        .build()
        );
    }
    public User findById(String loginID){
        return userRepository.findByLoginId(loginID).orElseThrow(()->new IllegalArgumentException("findById Not Found with id: "+loginID));
    }

    @Transactional
    public void updateStoreName(String storeName,String loginId){
        User user=userRepository.findByLoginId(loginId)
                .orElseThrow(()->new IllegalArgumentException("findById Not Found with id: "+loginId));
        user.setStoreName(storeName);
        userRepository.save(user);
    }

    public void addUserIcon(String url){
        authenticatedUser().updateUserIcon(url);
    }
    public void deleteUserIcon(){
        authenticatedUser().updateUserIcon(null);
    }

    public boolean isStoreNameSet(){
        if(authenticatedUser().getStoreName()!=null){
            return true;
        }
        return false;
    }

    public User authenticatedUser(){
        return userRepository.findByLoginId(SecurityUtil.getCurrentUserLoginId()).orElseThrow(()->new IllegalArgumentException("findById Not Found with id: "+SecurityUtil.getCurrentUserLoginId()));
    }

    //User의 지역 업데이트
    // 현재 지역을 지역 테이블에서 삭제 후 새로운 지역 삽입 및 User필드에 삽입
    @Transactional
    public void updateRegionToUser(RegionRequest request){
        User user= userRepository.findByLoginId(SecurityUtil.getCurrentUserLoginId()).orElseThrow(()->new IllegalArgumentException("User Not Found at id : "+SecurityUtil.getCurrentUserLoginId()));
        regionService.deleteRegion(user.getRegion().getId());
        Region region = regionService.save(request);
        user.updateRegion(region);
    }

    //사용자가 선택한 지역 삭제
    //지역 테이블에서 지역 삭제
//    @Transactional
//    public void deleteRegion(){
//        User user=userRepository.findByLoginId(SecurityUtil.getCurrentUserLoginId())
//                .orElseThrow(()->new IllegalArgumentException("findById Not Found with id: "+SecurityUtil.getCurrentUserLoginId()));
//        regionService.deleteRegion(user.getRegion().getId());
//        user.updateRegion(null);
//    }


}
