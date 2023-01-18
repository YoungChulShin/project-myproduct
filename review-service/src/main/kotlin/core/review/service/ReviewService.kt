package core.review.service

import common.model.core.review.Review
import common.model.error.exceptions.InvalidInputException
import org.springframework.stereotype.Service
import util.http.ServiceUtil

@Service
class ReviewService(
    private val serviceUtil: ServiceUtil,
) {

    fun getReviews(productId: Int): List<Review> {
        if (productId < 1) {
            throw InvalidInputException("Invalid productId: $productId")
        }
        if (productId == 213) {
            return listOf()
        }

        return listOf(
            Review(productId, 1, "Author 1", "Subject 1", "Content 1", serviceUtil.getServiceAddress()),
            Review(productId, 2, "Author 2", "Subject 2", "Content 2", serviceUtil.getServiceAddress()),
            Review(productId, 3, "Author 3", "Subject 3", "Content 3", serviceUtil.getServiceAddress())
        )
    }
}