package EST.Baemin.Manager.security.service;

import EST.Baemin.Manager.domain.Role;
import EST.Baemin.Manager.domain.User;
import EST.Baemin.Manager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
@AllArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(request);

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        User user = userRepository.findByLoginId(email)
                .orElseGet(() -> userRepository.save(
                        User.builder()
                                .loginId(email)
                                .nickname(name)
                                .role(Role.ROLE_USER)
                                .isActive(true)
                                .build()
                ));


        Map<String, Object> attributes = oAuth2User.getAttributes();

        return new CustomOAuth2User(
                Collections.singleton(() -> "ROLE_USER"),
                attributes,
                "email",
                user.getId()
        );
    }
}
