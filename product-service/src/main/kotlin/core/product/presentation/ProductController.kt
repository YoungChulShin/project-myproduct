package core.product.presentation

import common.model.core.product.Product
import core.product.service.ProductService
import org.springframework.web.bind.annotation.*

@RestController
class ProductController(
    private val productService: ProductService,
) {

    @PostMapping("/product")
    fun createProduct(@RequestBody body: Product): Product {
        return productService.createProduct(body)
    }

    @GetMapping("/product/{productId}")
    fun getProduct(@PathVariable productId: Int): Product {
        return productService.getProduct(productId)
    }

    @DeleteMapping("/product/{productId}")
    fun deleteProduct(@PathVariable productId: Int) {
        productService.deleteProduct(productId)
    }
}