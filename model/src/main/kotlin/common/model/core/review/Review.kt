package common.model.core.review

data class Review(
    val productId: Int,
    val reviewId: Int,
    val author: String,
    val subject: String,
    val content: String,
    val serviceAddress: String,
)