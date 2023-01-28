package core.product.domain

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "products")
class ProductEntity(
    @Indexed(unique = true)
    val productId: Int,
    var name: String,
    var weight: Int,
) {
    @Id
    var id: String? = null

    @Version
    var version: Int? = null


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (this.javaClass != other?.javaClass) return false

        other as ProductEntity

        if (productId != other.productId) return false
        if (name != other.name) return false
        if (weight != other.weight) return false
        if (id != other.id) return false
        if (version != other.version) return false

        return true
    }

    override fun hashCode(): Int {
        var result = productId
        result = 31 * result + name.hashCode()
        result = 31 * result + weight
        result = 31 * result + (id?.hashCode() ?: 0)
        result = 31 * result + (version ?: 0)
        return result
    }


}