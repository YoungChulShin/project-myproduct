package common.model.error.http

import java.time.ZonedDateTime

class HttpErrorInfo(
    val httpStatus: Int,
    val path: String,
    val message: String,
) {
    val timestamp: ZonedDateTime = ZonedDateTime.now()
}