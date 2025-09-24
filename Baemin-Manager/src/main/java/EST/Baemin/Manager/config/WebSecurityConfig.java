package EST.Baemin.Manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

//    @Bean
//    public WebSecurityCustomizer configure(){
//        return web->web.ignoring()
//                .requestMatchers("/pg-admin/**");
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(auth->
                auth.requestMatchers("/login","/signup").permitAll()    // 로그인, 회원가입은 인증 없이 접근 가능
                        .anyRequest().authenticated())
                .formLogin(auth-> auth.loginPage("/login")
                        .defaultSuccessUrl("/location-verification"))
                .logout(auth->auth.logoutSuccessUrl("/login")
                        .invalidateHttpSession(true)
                        .clearAuthentication(true));
        return httpSecurity.build();


    }
}
