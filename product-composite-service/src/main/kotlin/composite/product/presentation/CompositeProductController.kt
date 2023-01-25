package composite.product.presentation

import common.model.composite.product.ProductAggregate
import composite.product.service.CompositeProductService
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class CompositeProductController(
    private val compositeProductService: CompositeProductService,
) {

    @ApiOperation(value = "통합 제품 정보 조회")
    @ApiResponses(
        value = [
            ApiResponse(code = 400, message = "Bad request, invalid format of request")
        ]
    )
    @GetMapping("/product-composite/{productId}")
    fun getProduct(@PathVariable productId: Int): ProductAggregate {
        return compositeProductService.getProduct(productId)
    }
}