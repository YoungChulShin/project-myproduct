package core.product.presentation

import common.model.core.product.Product
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

    @GetMapping("/product/{productId}")
    fun getProduct(@PathVariable productId: Int): Product {
        return Product(1, "test", 2, "test2")
    }
}