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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ReviewEntity

        if (id != other.id) return false
        if (productId != other.productId) return false
        if (reviewId != other.reviewId) return false
        if (author != other.author) return false
        if (subject != other.subject) return false
        if (content != other.content) return false
        if (version != other.version) return false

        return true
    }

    override fun hashCode(): Int {
        var result = productId
        result = 31 * result + (id ?: 0)
        result = 31 * result + reviewId
        result = 31 * result + author.hashCode()
        result = 31 * result + subject.hashCode()
        result = 31 * result + content.hashCode()
        result = 31 * result + (version ?: 0)
        return result
    }
}