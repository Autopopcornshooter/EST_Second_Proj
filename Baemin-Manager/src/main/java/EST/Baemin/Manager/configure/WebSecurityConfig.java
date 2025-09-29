package EST.Baemin.Manager.configure;

import EST.Baemin.Manager.service.CustomOAuth2UserService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;


import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;


@EnableWebSecurity
@Configuration
@AllArgsConstructor
public class WebSecurityConfig {
private final CustomOAuth2UserService customOAuth2UserService;
    @Bean
    public WebSecurityCustomizer configure(){
        return web->web.ignoring()
                .requestMatchers("/css/**","/images/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    //배포용 보안 설정
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                .authorizeHttpRequests(auth->
                auth.requestMatchers("/home","/login","/signup").permitAll()// 로그인, 회원가입은 인증 없이 접근 가능
                        .requestMatchers(toH2Console()).permitAll()
                        .requestMatchers("/.well-known/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(auth-> auth.loginPage("/login")
                        .defaultSuccessUrl("/api/regions",true))
                .oauth2Login(oauth2->oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/api/regions",true)
                        .userInfoEndpoint(userInfo->userInfo
                                .userService(customOAuth2UserService)))// 구글 로그인 DB 연동 처리
                .logout(auth->auth
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true));

        httpSecurity.csrf(csrf -> csrf.ignoringRequestMatchers(toH2Console())); // ✅ CSRF 무시
        httpSecurity.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())); // ✅ frame 허용
        return httpSecurity.build();
    }



    //개발용 보안 설정
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
//        httpSecurity.authorizeHttpRequests(auth->auth.anyRequest().permitAll())
//                .formLogin(auth-> auth.loginPage("/login")
//                        .defaultSuccessUrl("/api/regions",true))
//                .logout(auth->auth.logoutSuccessUrl("/login")
//                        .invalidateHttpSession(true)
//                        .clearAuthentication(true));
//        httpSecurity.csrf(csrf -> csrf.ignoringRequestMatchers(toH2Console())); // ✅ CSRF 무시
//        httpSecurity.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())); // ✅ frame 허용
//        return httpSecurity.build();
//    }
//
//
//
}

