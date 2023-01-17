package composite.product.service

import common.model.core.product.Product
import common.model.core.recommendation.Recommendation
import common.model.core.review.Review
import common.model.error.exceptions.InvalidInputException
import common.model.error.exceptions.NotFoundException
import composite.product.domain.ProductIntegrator
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class CompositeProductServiceTest {

    companion object {
        private const val PRODUCT_ID_OK = 1
        private const val PRODUCT_ID_NOT_FOUND = 2
        private const val PRODUCT_ID_INVALID = 3
    }

    @BeforeEach
    fun setup() {
        `when`(integrator.getProduct(PRODUCT_ID_OK))
                .thenReturn(Product(PRODUCT_ID_OK, "test product", 1, "mock-product-address"))
        `when`(integrator.getRecommendations(PRODUCT_ID_OK))
                .thenReturn(listOf(Recommendation(PRODUCT_ID_OK, 1, "test author", 1, "hello", "mock-recommendation-address")))
        `when`(integrator.getReviews(PRODUCT_ID_OK))
                .thenReturn(listOf(Review(PRODUCT_ID_OK, 1, "test author", "subject", "content", "mock-review-address")))

        `when`(integrator.getProduct(PRODUCT_ID_NOT_FOUND))
                .thenThrow(NotFoundException("NOT FOUND: $PRODUCT_ID_NOT_FOUND"))
        `when`(integrator.getProduct(PRODUCT_ID_INVALID))
                .thenThrow(InvalidInputException("INVALID: $PRODUCT_ID_INVALID"))
    }

    @Autowired
    lateinit var client: WebTestClient

    @MockBean
    lateinit var integrator: ProductIntegrator

    @Test
    fun getProductById() {
        client.get()
                .uri("/product-composite/$PRODUCT_ID_OK")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.productId").isEqualTo(PRODUCT_ID_OK)
                .jsonPath("$.recommendations.length()").isEqualTo(1)
                .jsonPath("$.reviews.length()").isEqualTo(1)
    }

    @Test
    fun getProductNotFound() {
        client.get()
                .uri("/product-composite/$PRODUCT_ID_NOT_FOUND")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.path").isEqualTo("/product-composite/$PRODUCT_ID_NOT_FOUND")
                .jsonPath("$.message").isEqualTo("NOT FOUND: $PRODUCT_ID_NOT_FOUND")
    }

    @Test
    fun getProductInvalid() {
        client.get()
                .uri("/product-composite/$PRODUCT_ID_INVALID")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is4xxClientError
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.path").isEqualTo("/product-composite/$PRODUCT_ID_INVALID")
                .jsonPath("$.message").isEqualTo("INVALID: $PRODUCT_ID_INVALID")
    }
}