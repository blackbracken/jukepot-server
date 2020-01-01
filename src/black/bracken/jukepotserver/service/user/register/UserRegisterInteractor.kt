package black.bracken.jukepotserver.service.user.register

import arrow.core.Either
import arrow.core.extensions.fx
import arrow.core.rightIfNotNull
import black.bracken.jukepotserver.entity.domain.user.EmailAddress
import black.bracken.jukepotserver.entity.domain.user.Password
import black.bracken.jukepotserver.entity.domain.user.UserName
import black.bracken.jukepotserver.util.InvalidResponse
import java.util.*

class UserRegisterInteractor : UserRegisterUsecase {

    override fun invoke(inputTransferObject: UserRegisterUsecase.Input): Either<InvalidResponse, UUID> =
        Either.fx {
            val (email) = EmailAddress.of(inputTransferObject.email).rightIfNotNull { InvalidResponse("Address is invalid!") }
            val (password) = Password.of(inputTransferObject.password).rightIfNotNull { InvalidResponse("Password is invalid!") }
            val (userName) = UserName.of(inputTransferObject.userName).rightIfNotNull { InvalidResponse("UserName is invalid!") }

            // TODO: insert

            UUID.randomUUID()
        }

}