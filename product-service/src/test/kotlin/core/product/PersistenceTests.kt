package core.product

import core.product.domain.ProductEntity
import core.product.domain.ProductRepository
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest
import org.springframework.dao.DuplicateKeyException
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import java.util.stream.Collectors
import java.util.stream.IntStream

@DataMongoTest
class PersistenceTests {

    @Autowired
    private lateinit var repository: ProductRepository
    private lateinit var savedEntity: ProductEntity

    @BeforeEach
    fun setupDb() {
        repository.deleteAll()
        val entity = ProductEntity(1, "test-product", 1)
        savedEntity = repository.save(entity)
        Assertions.assertThat(entity).isEqualTo(savedEntity)
    }

    @Test
    fun create() {
        val newEntity = ProductEntity(2, "new-product", 2)
        savedEntity = repository.save(newEntity)

        val foundEntity = repository.findByProductId(newEntity.productId).get()
        Assertions.assertThat(newEntity).isEqualTo(foundEntity)
        Assertions.assertThat(repository.count()).isEqualTo(2)
    }

    @Test
    fun update() {
        savedEntity.name = "test-product2"
        repository.save(savedEntity)

        val foundEntity = repository.findByProductId(savedEntity.productId).get()
        Assertions.assertThat(foundEntity.version).isEqualTo(1)
        Assertions.assertThat(foundEntity.name).isEqualTo("test-product2")
    }

    @Test
    fun delete() {
        repository.delete(savedEntity)

        val foundEntity = repository.findByProductId(savedEntity.productId)
        Assertions.assertThat(foundEntity).isNotPresent
    }

    @Test
    fun getByProductId() {
        val foundEntity = repository.findByProductId(savedEntity.productId)

        Assertions.assertThat(foundEntity).isPresent
        Assertions.assertThat(foundEntity.get()).isEqualTo(savedEntity)
    }

    @Test
    fun duplicateError() {
        val newEntity = ProductEntity(savedEntity.productId, "test-product2", 1)

        Assertions
            .assertThatThrownBy { repository.save(newEntity) }
            .isInstanceOf(DuplicateKeyException::class.java)
    }

    @Test
    fun optimisticLocking() {
        val entity1 = repository.findByProductId(savedEntity.productId).get()
        val entity2 = repository.findByProductId(savedEntity.productId).get()

        entity1.name = "update-product"
        repository.save(entity1)

        try {
            entity2.name = "update-product2"
            repository.save(entity2)
            Assertions.fail("Expected optimistic locking fail")
        } catch (e: OptimisticLockingFailureException) { }

        val foundProduct = repository.findByProductId(savedEntity.productId).get()
        Assertions.assertThat(foundProduct.version).isEqualTo(1)
        Assertions.assertThat(foundProduct.name).isEqualTo("update-product")
    }

    @Test
    fun paging() {
        repository.deleteAll()

        // 10개 데이터 생성
        val newProducts = IntStream.rangeClosed(1001, 1010)
            .mapToObj { i -> ProductEntity(i, "name $i", i) }
            .collect(Collectors.toList())
        repository.saveAll(newProducts)

        val nextPage = PageRequest.of(0, 4, Sort.Direction.ASC, "productId")
        val firstProductPage = repository.findAll(nextPage)
        val secondProductPage = repository.findAll(firstProductPage.nextPageable())

        Assertions
            .assertThat(firstProductPage.content.map { p -> p.productId }.stream().collect(Collectors.toList()).toString())
            .isEqualTo("[1001, 1002, 1003, 1004]")
        Assertions
            .assertThat(secondProductPage.content.map { p -> p.productId }.stream().collect(Collectors.toList()).toString())
            .isEqualTo("[1005, 1006, 1007, 1008]")
    }
}