package composite.product.domain

import common.model.core.product.Product
import common.model.core.recommendation.Recommendation
import common.model.core.review.Review

interface ProductIntegrator {

    fun getProduct(productId: Int): Product

    fun getRecommendations(productId: Int): List<Recommendation>

    fun getReviews(productId: Int): List<Review>
}