package core.product.domain

import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface ProductRepository : PagingAndSortingRepository<ProductEntity, String> {

    fun findByProductId(productId: Int): Optional<ProductEntity>
}