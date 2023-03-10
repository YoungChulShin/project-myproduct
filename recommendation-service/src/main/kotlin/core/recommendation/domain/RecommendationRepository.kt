package core.recommendation.domain

import org.springframework.data.repository.CrudRepository

interface RecommendationRepository: CrudRepository<RecommendationEntity, String> {

    fun findByProductId(productId: Int): List<RecommendationEntity>
}