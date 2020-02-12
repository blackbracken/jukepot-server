package black.bracken.jukepotserver

import arrow.core.Either
import black.bracken.jukepotserver.ext.toJsonItem
import black.bracken.jukepotserver.presentation.UserPresentation
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import org.koin.ktor.ext.inject

internal fun Route.user() {
    val presentation by inject<UserPresentation>()

    post<RegisterRequest>("/register") { request ->
        when (val result = presentation.register(request.name, request.email, request.password)) {
            is Either.Right -> {
                call.respond(HttpStatusCode.OK, RegisterResponse(result.b))
            }
            is Either.Left -> {
                val errorResponse = result.a
                call.respond(errorResponse.statusCode, errorResponse.toJsonItem())
            }
        }
    }
}

private data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)

private data class RegisterResponse(
    val uuid: String
    // TODO: embrace token
)