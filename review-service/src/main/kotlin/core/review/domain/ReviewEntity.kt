package core.review.domain

import org.springframework.data.annotation.Version
import javax.persistence.*

@Entity
@Table(
    name = "reviews",
    indexes = [Index(name = "reviews_unique_idx", unique = true, columnList = "productId,reviewId")]
)
class ReviewEntity(
    val productId: Int,
    val reviewId: Int,
    val author: String,
    var subject: String,
    var content: String,
) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null

    @Version
    var version: Int? = null
}