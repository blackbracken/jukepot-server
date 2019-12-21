package black.bracken.jukepotserver.service.user.register

import arrow.core.Either
import black.bracken.jukepotserver.service.Usecase
import black.bracken.jukepotserver.util.InvalidResponse
import java.util.*

interface UserRegisterUsecase : Usecase<UserRegisterUsecase.Input, Either<InvalidResponse, UUID>> {
    data class Input(val email: String, val password: String, val userName: String)

    override operator fun invoke(inputTransferObject: Input): Either<InvalidResponse, UUID>
}