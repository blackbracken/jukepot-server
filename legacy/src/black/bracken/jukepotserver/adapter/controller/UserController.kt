package black.bracken.jukepotserver.adapter.controller

import arrow.core.Either
import black.bracken.jukepotserver.service.user.register.UserRegisterUsecase
import black.bracken.jukepotserver.util.InvalidResponse

class UserController(val registerUsecase: UserRegisterUsecase) {

    fun register(userName: String, email: String, password: String): Either<InvalidResponse, String> {
        val registerResult = registerUsecase(UserRegisterUsecase.Input(email, password, userName))

        return registerResult.map { uuid -> uuid.toString() }
    }

}