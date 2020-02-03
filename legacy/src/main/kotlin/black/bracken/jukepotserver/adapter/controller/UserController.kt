package black.bracken.jukepotserver.adapter.controller

import arrow.core.Either
import black.bracken.jukepotserver.InvalidResponse
import black.bracken.jukepotserver.usecase.RegisterUser

class UserController(val registerUseCase: RegisterUser) {

    fun register(userName: String, email: String, password: String): Either<InvalidResponse, String> {
        val registerResult = registerUseCase(RegisterUser.Input(email, password, userName))

        return registerResult.map { uuid -> uuid.toString() }
    }

}