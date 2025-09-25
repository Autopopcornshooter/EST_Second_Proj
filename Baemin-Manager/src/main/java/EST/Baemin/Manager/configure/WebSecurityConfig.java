package EST.Baemin.Manager.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public WebSecurityCustomizer configure(){
        return web->web.ignoring()
                .requestMatchers("/css/**","/images/**");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(auth->
                auth.requestMatchers("/home","/login","/signup").permitAll()// 로그인, 회원가입은 인증 없이 접근 가능
                        .requestMatchers(toH2Console()).permitAll()
                        .anyRequest().authenticated())
                .formLogin(auth-> auth.loginPage("/login")
                        .defaultSuccessUrl("/location-verification"))
                .logout(auth->auth.logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true));
        httpSecurity.csrf(csrf -> csrf.ignoringRequestMatchers(toH2Console())); // ✅ CSRF 무시
        httpSecurity.headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())); // ✅ frame 허용
        return httpSecurity.build();
    }
}
