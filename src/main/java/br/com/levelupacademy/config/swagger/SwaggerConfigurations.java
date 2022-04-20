package br.com.levelupacademy.config.swagger;

import br.com.levelupacademy.models.user.User;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfigurations {

    public Docket levelUpAcademyApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("br.com.levelupacademy"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .ignoredParameterTypes(User.class);
    }
}
