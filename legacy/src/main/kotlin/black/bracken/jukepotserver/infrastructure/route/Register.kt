package black.bracken.jukepotserver.infrastructure.route

import arrow.core.Either
import black.bracken.jukepotserver.adapter.controller.UserController
import black.bracken.jukepotserver.infrastructure.endpoint.request.RegisterRequest
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.Route
import io.ktor.routing.post
import org.koin.ktor.ext.inject

fun Route.register() {
    val userController by inject<UserController>()

    post<RegisterRequest>("/register") { register ->
        when (val result = userController.register(register.name, register.email, register.password)) {
            is Either.Right -> call.respondText(result.b)
            is Either.Left -> call.respondText(result.a.message)
        }
    }
}