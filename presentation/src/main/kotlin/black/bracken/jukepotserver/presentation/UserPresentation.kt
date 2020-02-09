package black.bracken.jukepotserver.presentation

import arrow.core.Either
import black.bracken.jukepotserver.ErrorResponse
import black.bracken.jukepotserver.usecase.RegisterUser

class UserPresentation(val registerUseCase: RegisterUser) {

    fun register(userName: String, email: String, password: String): Either<ErrorResponse, String> {
        val registerResult = registerUseCase(RegisterUser.Input(email, password, userName))

        return registerResult.map { uuid -> uuid.toString() }
    }

    data class RegisterRequest(
        val name: String,
        val email: String,
        val password: String
    )

}