package black.bracken.jukepotserver

import arrow.core.Either
import black.bracken.jukepotserver.presentation.UserPresentation
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.post
import org.koin.ktor.ext.inject

internal fun Route.user() {
    val presentation by inject<UserPresentation>()

    post<RegisterRequest>("/register") { request ->
        when (val result = presentation.register(request.name, request.email, request.password)) {
            is Either.Right -> call.respondText(result.b)
            is Either.Left -> call.respondText(result.a.message)
        }
    }
}

// TODO: move to better
data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)