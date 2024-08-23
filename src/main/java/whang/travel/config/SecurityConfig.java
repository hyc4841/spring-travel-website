package whang.travel.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private UserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 인가(접근 권한)을 성정
        //anyRequest().permitAll() 은 모든 url을 허용하겠다는 의미.
        http.authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/css/**").permitAll()
                                .requestMatchers("/script/**").permitAll()
                                .requestMatchers("/home").permitAll()
                                .requestMatchers("/signup/**").permitAll()
                                .requestMatchers("/").permitAll()
                                .requestMatchers("/accommodation/**").permitAll()
                                .anyRequest().authenticated()
                )
//                .userDetailsService(userDetailsService)
                // 로그인 관련 설정
                .formLogin((formLogin) ->
                        formLogin
                                .loginPage("/login")
                                .defaultSuccessUrl("/home")
                                .failureUrl("/home")
                                .usernameParameter("loginId")
                                .passwordParameter("password")
                                .defaultSuccessUrl("/", true)
                )

                // 로그아웃 관련 설정
                .logout((logout) ->
                        logout
                                .invalidateHttpSession(true)
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .logoutSuccessUrl("/logout?logout")
                );

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
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
























