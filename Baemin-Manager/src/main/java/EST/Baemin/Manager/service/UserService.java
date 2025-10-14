package EST.Baemin.Manager.service;

import EST.Baemin.Manager.dto.AddUserRequest;
import EST.Baemin.Manager.domain.User;
import EST.Baemin.Manager.dto.UserResponse;
import EST.Baemin.Manager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
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

    public Page<UserResponse> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                       .map(UserResponse::from);
    }

    public Page<UserResponse> searchUsersByName(String keyword, Pageable pageable) {
        return userRepository.findByNicknameContaining(keyword, pageable)
                       .map(UserResponse::from);
    }
}
