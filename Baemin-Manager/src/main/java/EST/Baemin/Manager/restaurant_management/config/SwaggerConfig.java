package EST.Baemin.Manager.restaurant_management.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                .title("Baemin Manager - 사장님을 위한 배민 서비스")
                .version("1.0")
                .description("식당 등록/수정/삭제/조회 API 문서"));
    }
}
