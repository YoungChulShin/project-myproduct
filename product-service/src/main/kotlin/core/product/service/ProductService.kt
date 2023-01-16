package core.product.service

import common.model.core.product.Product
import common.model.error.exceptions.InvalidInputException
import common.model.error.exceptions.NotFoundException
import org.springframework.stereotype.Service
import util.http.ServiceUtil

@Service
class ProductService(
    private val serviceUtil: ServiceUtil,
) {

    fun getProduct(productId: Int): Product {
        if (productId < 1) throw InvalidInputException("Invalid productId: $productId")
        if (productId == 13) throw NotFoundException("No product found for productId: $productId")

        return Product(productId, "name-${productId}", 123, serviceUtil.getServiceAddress())
    }
}