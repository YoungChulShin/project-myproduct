package core.recommendation.service

import common.model.core.recommendation.Recommendation
import common.model.error.exceptions.InvalidInputException
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import util.http.ServiceUtil

@Service
class RecommendationService(
    private val serviceUtil: ServiceUtil,
) {

    companion object {
        private val logger = LoggerFactory.getLogger(RecommendationService::class.java)
    }

    fun getRecommendations(productId: Int): List<Recommendation> {
        if (productId < 1) {
            throw InvalidInputException("Invalid product: $productId")
        }
        if (productId == 113) {
            return listOf()
        }

        return listOf(
            Recommendation(
                productId,
                1,
                "Author 1",
                1,
                "Content 1",
                serviceUtil.getServiceAddress()
            ),
            Recommendation(
                productId,
                2,
                "Author 2",
                2,
                "Content 2",
                serviceUtil.getServiceAddress()
            ),
            Recommendation(
                productId,
                3,
                "Author 3",
                3,
                "Content 3",
                serviceUtil.getServiceAddress()
            )
        )
    }
}