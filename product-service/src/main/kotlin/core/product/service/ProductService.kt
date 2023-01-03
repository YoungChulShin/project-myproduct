package core.product.service

import common.model.core.product.Product
import org.springframework.stereotype.Service
import util.http.ServiceUtil

@Service
class ProductService(
    private val serviceUtil: ServiceUtil,
) {

    fun getProduct(productId: Int) =
        Product(productId, "name-${productId}", 123, serviceUtil.getServiceAddress())
}