package composite.product.infrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import common.model.core.product.Product
import common.model.core.recommendation.Recommendation
import common.model.core.review.Review
import composite.product.domain.ProductIntegrator
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class ProductIntegratorImpl(
    private val restTemplate: RestTemplate,
    private val objectMapper: ObjectMapper,
) : ProductIntegrator {

    override fun getProduct(productId: Int): Product {
        TODO("Not yet implemented")
    }

    override fun getRecommendations(productId: Int): List<Recommendation> {
        TODO("Not yet implemented")
    }

    override fun getReviews(productId: Int): List<Review> {
        TODO("Not yet implemented")
    }
}