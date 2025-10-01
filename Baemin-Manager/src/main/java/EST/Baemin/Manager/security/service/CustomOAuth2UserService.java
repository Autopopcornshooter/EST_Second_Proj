package EST.Baemin.Manager.security.service;

import EST.Baemin.Manager.domain.User;
import EST.Baemin.Manager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@AllArgsConstructor
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final UserRepository userRepository;


    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException{
            OAuth2User oAuth2User=new DefaultOAuth2UserService().loadUser(request);

            String email=oAuth2User.getAttribute("email");
            String name=oAuth2User.getAttribute("name");

            if(userRepository.findByLoginId(email).isEmpty()){
                userRepository.save(
                        User.builder()
                                .loginId(email)
                                .nickname(name)
                                .build()
                );
            }
        return new DefaultOAuth2User(
                Collections.singleton(() -> "ROLE_USER"), // 권한
                oAuth2User.getAttributes(),              // 구글에서 받은 사용자 정보
                "email"                                  // 기본 키로 삼을 속성
        );

    }
}
