package core.review.domain

import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

interface ReviewRepository: CrudRepository<ReviewEntity, Int> {

    @Transactional(readOnly = true)
    fun findByProductId(productId: Int): List<ReviewEntity>
}