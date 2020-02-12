package black.bracken.jukepotserver

import io.ktor.http.HttpStatusCode

class ErrorResponse(val statusCode: HttpStatusCode, val reason: String)

@Suppress("FunctionName")
fun InvalidParameter(parameterName: String): ErrorResponse =
    ErrorResponse(HttpStatusCode.BadRequest, "Request parameter didn't validate: $parameterName")

@Suppress("FunctionName")
fun InternalError(reason: String = "Something happened."): ErrorResponse =
    ErrorResponse(HttpStatusCode.InternalServerError, reason)