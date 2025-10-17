package EST.Baemin.Manager.service;

import EST.Baemin.Manager.domain.Region;
import EST.Baemin.Manager.domain.Role;
import EST.Baemin.Manager.dto.SignupRequest;
import EST.Baemin.Manager.domain.User;
import EST.Baemin.Manager.dto.UserResponse;
import EST.Baemin.Manager.dto.RegionRequest;
import EST.Baemin.Manager.repository.UserRepository;
import EST.Baemin.Manager.util.SecurityUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private PasswordEncoder encoder;

    public User save(SignupRequest request){
        log.debug(request.getUsername());
        log.debug("DB에 데이터 삽입");
        return userRepository.save(
                User.builder()
                        .loginId(request.getUsername())
                        .password(encoder.encode(request.getPassword()))
                        .nickname(request.getNickname())
                        .storeName(request.getStoreName())
                        .role(Role.ROLE_USER)
                        .build()
        );
    }
    public User findById(String loginID){
        return userRepository.findByLoginId(loginID).orElseThrow(()->new IllegalArgumentException("findById Not Found with id: "+loginID));
    }

    public boolean isLoginIdExist(String loginId){
        return userRepository.existsByLoginId(loginId);
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
        return userRepository.findByLoginId(SecurityUtil.getCurrentUserLoginId())
                .orElseThrow(
                        ()->new IllegalArgumentException("findById Not Found with id: "+SecurityUtil.getCurrentUserLoginId()
                        ));
    }

    //User의 지역 업데이트
    // 현재 지역을 지역 테이블에서 삭제 후 새로운 지역 삽입 및 User필드에 삽입
    @Transactional
    public Region updateRegionToUser(Region region){
        User user= authenticatedUser();
        user.updateRegion(region);
        return region;
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


    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                       .map(UserResponse::from);
    }

    public Page<UserResponse> searchUsersByName(String keyword, Pageable pageable) {
        return userRepository.findByNicknameContaining(keyword, pageable)
                       .map(UserResponse::from);
    }

    @Transactional
    public UserResponse updateUserStatus(Long id) {
        User user = userRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        user.setActive(!user.isActive());
        userRepository.save(user);

        return UserResponse.from(user);
    }

    // 로그인한 유저의 주소 가져오기
    public String getLoggedInUserAddress(){
        User user = authenticatedUser();
        if (user.getRegion() == null) return null;
        // region 엔티티의 address 데이터
        return user.getRegion().getAddress();
    }
}
