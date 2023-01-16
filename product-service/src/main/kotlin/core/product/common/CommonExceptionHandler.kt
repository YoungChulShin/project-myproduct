package core.product.common

import common.model.error.exceptions.InvalidInputException
import common.model.error.exceptions.NotFoundException
import common.model.error.http.HttpErrorInfo
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class CommonExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = [NotFoundException::class])
    fun handleReactiveNotFoundException(e: Exception, request: HttpServletRequest) =
            createHttpErrorInfo(request, HttpStatus.NOT_FOUND, e)

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = [InvalidInputException::class])
    fun handleInvalidInputException(e: Exception, request: HttpServletRequest) =
            createHttpErrorInfo(request, HttpStatus.UNPROCESSABLE_ENTITY, e)

    private fun createHttpErrorInfo(
            request: HttpServletRequest,
            httpStatus: HttpStatus,
            e: Exception): HttpErrorInfo {
        val path = request.requestURI
        val message = e.message ?: ""

        return HttpErrorInfo(httpStatus.value(), path, message)
    }
}

