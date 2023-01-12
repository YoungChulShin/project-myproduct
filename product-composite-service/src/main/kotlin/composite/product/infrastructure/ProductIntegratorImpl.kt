package composite.product.infrastructure

import com.fasterxml.jackson.databind.ObjectMapper
import common.model.core.product.Product
import common.model.core.recommendation.Recommendation
import common.model.core.review.Review
import composite.product.config.ServerConnectionConfig
import composite.product.domain.ProductIntegrator
import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.RestTemplate
import util.exceptions.InvalidInputException
import util.exceptions.NotFoundException
import util.http.HttpErrorInfo
import java.io.IOException

@Component
class ProductIntegratorImpl(
    private val restTemplate: RestTemplate,
    private val objectMapper: ObjectMapper,
    private val serverConnectionConfig: ServerConnectionConfig,
) : ProductIntegrator {

    companion object {
        private val logger = LoggerFactory.getLogger(ProductIntegratorImpl::class.java)
    }

    override fun getProduct(productId: Int): Product {
        val url = serverConnectionConfig.productService.getUrl("product/${productId}")
        return restTemplate.getForObject(url, Product::class.java) ?: throw NotFoundException()
    }

    override fun getRecommendations(productId: Int): List<Recommendation> {
        try {
            val url = serverConnectionConfig.recommendationService.getUrl("recommendation?productId=${productId}")
            return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<List<Recommendation>>(){}
            ).body ?: listOf()
        } catch (ex: HttpClientErrorException) {
            when(ex.statusCode) {
                HttpStatus.NOT_FOUND -> throw NotFoundException(getErrorMessage(ex))
                HttpStatus.UNPROCESSABLE_ENTITY -> throw InvalidInputException(getErrorMessage(ex))
                else -> {
                    logger.warn("Got a unexpected HTTP error: {}, will rethrow it", ex.statusCode)
                    logger.warn("Error body: {}", ex.responseBodyAsString)
                    throw ex
                }
            }
        } catch (ex: Exception) {
            logger.error("internal service error: {}", ex.message)
            return listOf()
        }
    }

    override fun getReviews(productId: Int): List<Review> {
        try {
            val url = serverConnectionConfig.reviewService.getUrl("reviews?productId=${productId}")
            return restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<List<Review>>() { }
            ).body ?: listOf()
        } catch (ex: HttpClientErrorException) {
            when(ex.statusCode) {
                HttpStatus.NOT_FOUND -> throw NotFoundException(getErrorMessage(ex))
                HttpStatus.UNPROCESSABLE_ENTITY -> throw InvalidInputException(getErrorMessage(ex))
                else -> {
                    logger.warn("Got a unexpected HTTP error: {}, will rethrow it", ex.statusCode)
                    logger.warn("Error body: {}", ex.responseBodyAsString)
                    throw ex
                }
            }
        } catch (ex: Exception) {
            logger.error("internal service error: {}", ex.message)
            return listOf()
        }
    }

    private fun getErrorMessage(ex: HttpClientErrorException): String {
        return try {
            objectMapper.readValue(ex.responseBodyAsString, HttpErrorInfo::class.java).message
        } catch (ioEx: IOException) {
            ex.message ?: ""
        }
    }
}