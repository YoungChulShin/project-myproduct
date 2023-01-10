package composite.product.service

import common.model.composite.product.ProductAggregate
import common.model.composite.product.RecommendationSummary
import common.model.composite.product.ReviewSummary
import common.model.composite.product.ServiceAddresses
import common.model.core.product.Product
import common.model.core.recommendation.Recommendation
import common.model.core.review.Review
import composite.product.domain.ProductIntegrator
import org.springframework.stereotype.Service
import util.http.ServiceUtil
import java.util.stream.Collectors

@Service
class CompositeProductService(
    private val integrator: ProductIntegrator,
    private val serviceUtil: ServiceUtil,
) {

    fun getProduct(productId: Int): ProductAggregate {
        val product = integrator.getProduct(productId)
        val recommendations = integrator.getRecommendations(productId)
        val reviews = integrator.getReviews(productId)

        return createProductAggregate(product, recommendations, reviews, serviceUtil.getServiceAddress())
    }

    private fun createProductAggregate(
        product: Product,
        recommendations: List<Recommendation>,
        reviews: List<Review>,
        serviceAddress: String,
    ): ProductAggregate {
        val productId = product.productId
        val name = product.name
        val weight = product.weight

        val recommendationSummaries = recommendations.stream()
            .map { r -> RecommendationSummary(r.recommendationId, r.author, r.rate, r.content) }
            .collect(Collectors.toList()) as List<RecommendationSummary>

        val reviewSummaries = reviews.stream()
            .map { r -> ReviewSummary(r.reviewId, r.author, r.subject, r.content) }
            .collect(Collectors.toList()) as List<ReviewSummary>

        val productAddress = product.serviceAddress
        val reviewAddress = if (reviews.isNotEmpty()) reviews[0].serviceAddress else ""
        val recommendationAddress = if (recommendations.isNotEmpty()) recommendations[0].serviceAddress else ""
        val serviceAddresses = ServiceAddresses(serviceAddress, productAddress, reviewAddress, recommendationAddress)

        return ProductAggregate(
            productId = productId,
            name = name,
            weight = weight,
            recommendations = recommendationSummaries,
            reviews = reviewSummaries,
            serviceAddresses = serviceAddresses,
        )

    }
}