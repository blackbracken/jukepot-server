package black.bracken.jukepotserver.port

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import black.bracken.jukepotserver.domain.UserToken
import black.bracken.jukepotserver.domain.user.EmailAddress
import black.bracken.jukepotserver.domain.user.Password
import black.bracken.jukepotserver.domain.user.UserName
import black.bracken.jukepotserver.util.InvalidResponse
import black.bracken.jukepotserver.util.PasswordHashGenerator
import java.util.*

class UserController {

    companion object {
        private const val SALT_LENGTH = 32
    }

    fun register(nameText: String, addressText: String, passwordText: String): Either<InvalidResponse, UserToken> {
        val uuid = UUID.randomUUID().toString()
        val passwordHash = PasswordHashGenerator.generate(passwordText, SALT_LENGTH)
            ?: return InvalidResponse().left()

        // TODO: remove; test domain validation
        val address = EmailAddress.of(addressText) ?: return InvalidResponse("Email is invalid!").left()
        val userName = UserName.of(nameText) ?: return InvalidResponse("Name is invalid!").left()
        val password = Password.of(passwordText) ?: return InvalidResponse("Password is invalid!").left()

        println("UUID: $uuid")
        println("Name: $nameText")
        println("Email: $address")
        println("Password(hashed): ${passwordHash.hash}")
        println("Salt: ${passwordHash.salt}")

        return "NICE TOKEN".right()
    }

}