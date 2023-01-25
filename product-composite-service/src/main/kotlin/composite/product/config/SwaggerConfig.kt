package composite.product.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

    @Bean
    fun apiDocument(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("composite.product.presentation"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(
                ApiInfo(
                    "my product",
                    "my sample project",
                    "1.0.0",
                    "",
                    Contact(
                        "shin yc",
                        "my url",
                        "my@gmail.com"
                    ),
                    "license 2023",
                    "license url",
                    emptyList()
                )
            )
    }
}