package common.model.core.recommendation

data class Recommendation(
    val productId: Int,
    val recommendationId: Int,
    val author: String,
    val rate: Int,
    val content: String,
    val serviceAddress: String,
) {
    private constructor() : this(-1, -1, "", -1, "", "")
}