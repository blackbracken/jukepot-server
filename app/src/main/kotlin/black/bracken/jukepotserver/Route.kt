package black.bracken.jukepotserver

import black.bracken.jukepotserver.adapter.controller.UserController
import black.bracken.jukepotserver.infrastructure.endpoint.request.RegisterRequest
import io.ktor.routing.Route
import io.ktor.routing.post
import org.koin.ktor.ext.inject

internal fun Route.user() {
    val userController by inject<UserController>()

    post<RegisterRequest>("/register") { request ->
        userController.register(userName = request.name, email = request.email, password = request.password)
    }
}