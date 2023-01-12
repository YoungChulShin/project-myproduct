package composite.product.presentation

import common.model.composite.product.ProductAggregate
import composite.product.service.CompositeProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CompositeProductController(
    private val compositeProductService: CompositeProductService,
) {

    @GetMapping("/product-composite/{productId}")
    fun getProduct(@PathVariable productId: Int): ProductAggregate {
        return compositeProductService.getProduct(productId)
    }
}