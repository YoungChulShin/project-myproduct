package composite.product.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "app")
class ServerConnectionConfig (
    val productService: ServiceConnectionInfo,
    val reviewService: ServiceConnectionInfo,
    val recommendationService: ServiceConnectionInfo,
)

data class ServiceConnectionInfo(
    val host: String,
    val port: Int,
) {
    fun getUrl(path: String): String {
        val normalizedUrl = if (path.startsWith("/")) path.substring(1, path.length) else path
        return "http://${this.host}:${this.port}/${normalizedUrl}"
    }
}