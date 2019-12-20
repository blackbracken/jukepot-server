package black.bracken.jukepotserver.ext

import io.ktor.application.ApplicationCall
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond

suspend fun ApplicationCall.respondError(statusCode: HttpStatusCode, vararg errorMessages: String) {
    respond(statusCode, ErrorResponse(errorMessages.toList()))
}

private data class ErrorResponse(val errors: List<String>)