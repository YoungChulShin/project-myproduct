package composite.product.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.service.StringVendorExtension
import springfox.documentation.service.VendorExtension
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2
import kotlin.contracts.contract

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
//            .apiInfo(ApiInfo(
//                title = "my product",
//                description = "my sample project",
//                version = "1.0.0",
//                termsOfServiceUrl = "",
//                contact = Contact(
//                    "shin yc",
//                    "my url",
//                    "my@gmail.com"),
//                license = "license 2023",
//                licenseUrl = "license url"
//            ))
    }
}