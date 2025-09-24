package EST.Baemin.Manager.service;

import EST.Baemin.Manager.dto.AddUserRequest;
import EST.Baemin.Manager.entity.User;
import EST.Baemin.Manager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private PasswordEncoder encoder;

    public User save(AddUserRequest request){
        return userRepository.save(
                User.builder()
                        .loginId(request.getLoginId())
                        .password(encoder.encode(request.getPassword()))
                        .build()
        );
    }

}
