package core.product.domain

import org.springframework.data.annotation.Id
import org.springframework.data.annotation.Version
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document(collation = "products")
class ProductEnity(
    @Indexed(unique = true)
    val productId: Int,
    var name: String,
    var weight: Int,
) {
    @Id
    var id: String? = null

    @Version
    var version: Int? = null

}