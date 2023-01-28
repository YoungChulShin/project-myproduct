package core.review

import core.review.domain.ReviewEntity
import core.review.domain.ReviewRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.annotation.Rollback
import org.springframework.transaction.annotation.Transactional

@DataJpaTest
@Rollback(true)
class PersistenceTests {

    @Autowired
    private lateinit var repository: ReviewRepository
    private lateinit var savedEntity: ReviewEntity

    @BeforeEach
    fun setup() {
        repository.deleteAll()
        val entity = ReviewEntity(1, 1, "a", "s", "c")
        savedEntity = repository.save(entity)
        Assertions.assertThat(savedEntity).isEqualTo(entity)
    }

    @Test
    @Transactional
    fun create() {
        val newEntity = ReviewEntity(2, 2, "a1", "s1", "c1")
        savedEntity = repository.save(newEntity)

        val foundEntity = repository.findByProductId(2).get(0)
        Assertions.assertThat(foundEntity).isEqualTo(newEntity)
        Assertions.assertThat(repository.count()).isEqualTo(2)
    }

    @Test
    @Transactional
    fun update() {
        savedEntity.content = "c2"
        savedEntity = repository.save(savedEntity)

        val foundEntity = repository.findByProductId(1).get(0)
        Assertions.assertThat(foundEntity).isEqualTo(savedEntity)
        Assertions.assertThat(foundEntity.content).isEqualTo("c2")
    }

    @Test
    @Transactional
    fun delete() {
        repository.delete(savedEntity)

        val foundEntities = repository.findByProductId(1)
        Assertions.assertThat(foundEntities.size).isEqualTo(0)
    }
}