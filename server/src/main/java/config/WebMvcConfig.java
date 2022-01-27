package config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//이걸로 안되서 컨트롤러에서 어노테이션 사용함...
@Configuration //스프링 빈으로 등록한다.
public class WebMvcConfig implements WebMvcConfigurer {
    private final long MAX_AGE_SECS = 3600; //요청 만료 시간?

    @Override
    public void addCorsMappings(CorsRegistry registry){
        //모든 경로에 대해 설정
        registry.addMapping("/**")
                //3000
                .allowedOrigins("http://localhost:3000")
                //허용 메서드 타입
                .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(MAX_AGE_SECS);
    }

}
