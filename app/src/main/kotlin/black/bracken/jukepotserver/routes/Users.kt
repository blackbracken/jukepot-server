package black.bracken.jukepotserver.routes

import arrow.core.Either
import black.bracken.jukepotserver.ext.toJsonObject
import black.bracken.jukepotserver.presentation.UserPresentation
import io.ktor.application.call
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import kotlinx.serialization.json.json
import org.koin.ktor.ext.inject

private const val ROOT = "/users"

internal fun Route.users() {
    val presentation by inject<UserPresentation>()

    post<RegisterRequest>("$ROOT/register") { request ->
        when (val result = presentation.register(request.name, request.email, request.password)) {
            is Either.Right -> {
                call.respond(json {
                    "uuid" to result.b
                })
            }
            is Either.Left -> {
                val errorResponse = result.a
                call.respond(errorResponse.statusCode, errorResponse.toJsonObject())
            }
        }
    }
}

private data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)