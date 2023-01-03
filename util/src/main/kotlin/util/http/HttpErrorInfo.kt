package util.http

import org.springframework.http.HttpStatus
import java.time.ZonedDateTime

class HttpErrorInfo(
    val httpStatus: HttpStatus,
    val path: String,
    val message: String,
) {
    val timestamp: ZonedDateTime = ZonedDateTime.now()
}