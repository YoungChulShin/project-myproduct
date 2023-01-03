package core.product.presentation

import core.product.service.ProductService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productService: ProductService,
) {

    @GetMapping("/product/{productId}")
    fun getProduct(@PathVariable productId: Int) = productService.getProduct(productId)
}