package composite.product.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConfigurationProperties(prefix = "app")
@ConstructorBinding
class ServiceConfig (
    val productService: ServiceConnectionInfo,
    val reviewService: ServiceConnectionInfo,
    val recommendationService: ServiceConnectionInfo,
)

data class ServiceConnectionInfo(
    val host: String,
    val port: Int,
)