package black.bracken.jukepotserver.presentation

import arrow.core.Either
import black.bracken.jukepotserver.ErrorResponse
import black.bracken.jukepotserver.usecase.RegisterUser

class UserPresentation(val registerUseCase: RegisterUser) {

    fun register(userName: String, email: String, password: String): Either<ErrorResponse, String> {
        val tokenOrError = registerUseCase(RegisterUser.Input(email, password, userName))

        return tokenOrError.map { userToken -> userToken.uuid.toString() }
    }

}