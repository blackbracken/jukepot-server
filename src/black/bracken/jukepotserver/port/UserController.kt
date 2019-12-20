package black.bracken.jukepotserver.port

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import black.bracken.jukepotserver.domain.UserToken
import black.bracken.jukepotserver.util.InvalidResponse
import black.bracken.jukepotserver.util.PasswordHashGenerator
import java.util.*

class UserController {

    companion object {
        private const val SALT_LENGTH = 32
    }

    fun register(name: String, email: String, password: String): Either<InvalidResponse, UserToken> {
        val uuid = UUID.randomUUID().toString()
        val passwordHash = PasswordHashGenerator.generate(password, SALT_LENGTH) ?: return InvalidResponse().left()

        when { // TODO: for test; remove
            email.isEmpty() -> return InvalidResponse("Email must not empty!").left()
            name.isEmpty() -> return InvalidResponse("Name must not empty!").left()
            password.isEmpty() -> return InvalidResponse("Password must not empty!").left()
        }

        println("UUID: $uuid")
        println("Name: $name")
        println("Email: $email")
        println("Password(hashed): ${passwordHash.hash}")
        println("Salt: ${passwordHash.salt}")

        return "NICE TOKEN".right()
    }

}