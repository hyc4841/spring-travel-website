package whang.travel.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 인가(접근 권한)을 성정
        //anyRequest().permitAll() 은 모든 url을 허용하겠다는 의미.
        http
//                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/css/**").permitAll()
                                .requestMatchers("/script/**").permitAll()
                                .requestMatchers("/img/**").permitAll()
                                .requestMatchers("/home").permitAll()
                                .requestMatchers("/signup/**").permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/bulletinBoard/**").permitAll()
                                .anyRequest().authenticated()
                )

                // 로그인 관련 설정
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .usernameParameter("loginId")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/home", true)
                )

                // 로그아웃 관련 설정
                .logout((logout) ->
                        logout
                                .invalidateHttpSession(true)
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/home")
                )
                .userDetailsService(userDetailsService);

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


}

/*
    spring security 도입하기
    이거 도입하려면 먼저 SecurityFilterChain를 사용해야함
    WebSecurityConfigurerAdapter이거 사라지고 SecurityFilterChain이 생김
    bean을 통해 설정이 이루어지고 상속이 사라짐 개굳

    회원가입에 도입하기
    수동 :
    BcryptPassword로 비밀번호 암호화해서 데이터베이스에 저장(BcryptPassword 말고 다른거 써도 됨. 이게 근데 제일 많이 쓰는 듯)




 */
























