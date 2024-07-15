package whang.travel;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import whang.travel.web.interceptor.LoginCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer { // 인터셉터를 등록하기 위해서 WebMvcConfigurer를 상속받음


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**") // 모든 경로에 적용
                .excludePathPatterns("/", "/signup/add", "/login", "/logout", "/css/**", "/static/**" ,
                                    "/script/**", "/*.ico", "/error", "/home"); // 적용하지 않을 경로 설정
    }
}
