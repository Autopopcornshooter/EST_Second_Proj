package EST.Baemin.Manager.service;

import EST.Baemin.Manager.domain.Region;
import EST.Baemin.Manager.dto.AddUserRequest;
import EST.Baemin.Manager.domain.User;
import EST.Baemin.Manager.dto.RegionRequest;
import EST.Baemin.Manager.repository.RegionRepository;
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
    private final RegionRepository regionRepository;
    private PasswordEncoder encoder;

    public User save(AddUserRequest request){
        return userRepository.save(
                User.builder()
                        .loginId(request.getUsername())
                        .password(encoder.encode(request.getPassword()))
                        .nickname(request.getNickname())
                        .storeName(request.getStoreName())
                        .build()
        );
    }

    @Transactional
    public void addRegionToUser(RegionRequest request){
        User user= userRepository.findByLoginId(SecurityUtil.getCurrentUsername()).orElseThrow(()->new IllegalArgumentException("User Not Found at id : "+SecurityUtil.getCurrentUsername()));
        Region region=regionRepository.save(request.toEntity());
        user.updateRegion(region);
    }

}
