package util.http

import org.springframework.http.HttpStatus
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import util.exceptions.InvalidInputException
import util.exceptions.NotFoundException

@RestControllerAdvice
class GlobalControllerExceptionHandler {

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = [NotFoundException::class])
    fun handleNotFoundException(request: ServerHttpRequest, e: Exception) =
        createHttpErrorInfo(HttpStatus.NOT_FOUND, request, e)

    @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(value = [InvalidInputException::class])
    fun handleInvalidInputException(request: ServerHttpRequest, e: Exception) =
        createHttpErrorInfo(HttpStatus.UNPROCESSABLE_ENTITY, request, e)

    private fun createHttpErrorInfo(httpStatus: HttpStatus, request: ServerHttpRequest, e: Exception): HttpErrorInfo {
        val path = request.path.pathWithinApplication().value()
        val message = e.message ?: ""

        return HttpErrorInfo(httpStatus, path, message)
    }
}