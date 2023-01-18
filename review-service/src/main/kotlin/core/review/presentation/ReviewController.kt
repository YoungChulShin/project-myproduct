package core.review.presentation

import common.model.core.review.Review
import core.review.service.ReviewService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class ReviewController(
    private val reviewService: ReviewService,
) {
    
    @GetMapping("/reviews")
    fun getReviews(@RequestParam productId: Int): List<Review> {
        return reviewService.getReviews(productId)
    }
}