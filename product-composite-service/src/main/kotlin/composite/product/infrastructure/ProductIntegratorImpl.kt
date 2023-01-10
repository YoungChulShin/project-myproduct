package composite.product.infrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import common.model.core.product.Product
import common.model.core.recommendation.Recommendation
import common.model.core.review.Review
import composite.product.config.ServerConnectionConfig
import composite.product.domain.ProductIntegrator
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import util.exceptions.NotFoundException

@Component
class ProductIntegratorImpl(
    private val restTemplate: RestTemplate,
    private val objectMapper: ObjectMapper,
    private val serverConnectionConfig: ServerConnectionConfig,
) : ProductIntegrator {

    override fun getProduct(productId: Int): Product {
        val url = serverConnectionConfig.productService.getUrl("product/${productId}")
        return restTemplate.getForObject(url, Product::class.java) ?: throw NotFoundException()
    }

    override fun getRecommendations(productId: Int): List<Recommendation> {
        val url = serverConnectionConfig.recommendationService.getUrl("recommendation?productId=${productId}")
        return restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<Recommendation>>(){}
        ).body ?: listOf()
    }

    override fun getReviews(productId: Int): List<Review> {
        val url = serverConnectionConfig.reviewService.getUrl("reviews?productId=${productId}")
        return restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<Review>>() { }
        ).body ?: listOf()
    }
}