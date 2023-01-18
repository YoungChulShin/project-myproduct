package core.recommendation.presentation

import common.model.core.recommendation.Recommendation
import core.recommendation.service.RecommendationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RecommendationController(
    private val recommendationService: RecommendationService,
) {

    @GetMapping("/recommendation")
    fun getRecommendations(@RequestParam productId: Int): List<Recommendation> {
        return recommendationService.getRecommendations(productId)
    }
}