package answer.king;

import com.google.common.base.Predicate;
import org.h2.server.web.WebServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@EnableSwagger2
@SpringBootApplication
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

	@Bean
	public ServletRegistrationBean h2ServletRegistration() {
		return new ServletRegistrationBean(new WebServlet(), "/h2/*");
	}

	@Bean
	public Docket swaggerSpringMvcPlugin() {
		Predicate<String> apiPaths = or(regex("/order.*"), regex("/item.*"));
		return new Docket(DocumentationType.SWAGGER_2)
			.select()
			.paths(apiPaths)
			.build();
	}
}
