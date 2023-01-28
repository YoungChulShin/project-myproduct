package core.product.service

import common.model.core.product.Product
import common.model.error.exceptions.InvalidInputException
import common.model.error.exceptions.NotFoundException
import core.product.domain.ProductRepository
import org.springframework.dao.DuplicateKeyException
import org.springframework.stereotype.Service
import util.http.ServiceUtil

@Service
class ProductService(
    private val repository: ProductRepository,
    private val mapper: ProductMapper,
    private val serviceUtil: ServiceUtil,
) {

    fun createProduct(product: Product): Product {
        try {
            val entity = mapper.apiToEntity(product)
            val savedEntity = repository.save(entity)
            return mapper.entotyToApi(savedEntity)
        } catch (e: DuplicateKeyException) {
            throw InvalidInputException("Duplicate key, Product Id: ${product.productId}")
        }
    }

    fun getProduct(productId: Int): Product {
        if (productId < 1) {
            throw InvalidInputException("Invalid productId: $productId")
        }

        val entity = repository.findByProductId(productId)
            .orElseThrow { NotFoundException("No product found for productId: $productId") }
        val response = mapper.entotyToApi(entity)
        response.serviceAddress = serviceUtil.getServiceAddress()

        return response
    }

    fun deleteProduct(productId: Int) {
        repository.findByProductId(productId).ifPresent { repository.delete(it) }
    }

}