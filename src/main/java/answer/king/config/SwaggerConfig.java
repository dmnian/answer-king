package answer.king.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        Predicate<String> apiPaths = or(regex("/order.*"), regex("/item.*"));
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(apiPaths)
                .build();
    }
}
