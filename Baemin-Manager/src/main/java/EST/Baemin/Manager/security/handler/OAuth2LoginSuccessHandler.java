package EST.Baemin.Manager.security.handler;

import EST.Baemin.Manager.domain.User;
import EST.Baemin.Manager.repository.UserRepository;
import EST.Baemin.Manager.security.service.CustomOAuth2UserService;
import EST.Baemin.Manager.util.SecurityUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
@Slf4j
@Component
@AllArgsConstructor
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private final UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        User user = userRepository.findByLoginId(SecurityUtil.getCurrentUserLoginId()).orElseThrow();
        if(user.getStoreName()==null){
            getRedirectStrategy().sendRedirect(request, response, "/storeName");
            return;
        }
        //로그인 되어 있는 상태에서 지역이 지정되지 않았다면 지역 지정 페이지로 이동
        if(user.getRegion()==null) {
            getRedirectStrategy().sendRedirect(request, response, "/api/regions");
        }else{
            getRedirectStrategy().sendRedirect(request, response, "/api/restaurants");
        }
    }
}
