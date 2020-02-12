package black.bracken.jukepotserver.routes

import arrow.core.Either
import black.bracken.jukepotserver.ext.toJsonItem
import black.bracken.jukepotserver.presentation.UserPresentation
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.post
import org.koin.ktor.ext.inject

private const val ROOT = "/users"

internal fun Route.users() {
    val presentation by inject<UserPresentation>()

    post<NewRequest>("$ROOT/new") { request ->
        when (val result = presentation.register(request.name, request.email, request.password)) {
            is Either.Right -> {
                call.respond(HttpStatusCode.OK, NewResponse(result.b))
            }
            is Either.Left -> {
                val errorResponse = result.a
                call.respond(errorResponse.statusCode, errorResponse.toJsonItem())
            }
        }
    }
}

private data class NewRequest(
    val name: String,
    val email: String,
    val password: String
)

private data class NewResponse(
    val uuid: String
)