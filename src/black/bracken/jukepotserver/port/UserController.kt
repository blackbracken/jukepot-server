package black.bracken.jukepotserver.port

import arrow.core.Either
import black.bracken.jukepotserver.domain.UserToken
import black.bracken.jukepotserver.util.PasswordHashGenerator
import java.util.*

class UserController {

    companion object {
        private const val SALT_LENGTH = 32
    }

    suspend fun register(name: String, email: String, password: String): Either<Throwable, UserToken> =
        Either.catch {
            val uuid = UUID.randomUUID().toString()
            val passwordHash = PasswordHashGenerator.generate(password, SALT_LENGTH)
                ?: throw IllegalStateException("Failed to generate hash") // TODO: validate better

            when { // TODO: for test; remove
                email.isEmpty() -> throw IllegalArgumentException("Email must not empty!")
                name.isEmpty() -> throw IllegalArgumentException("Name must not empty!")
                password.isEmpty() -> throw IllegalArgumentException("Password must not empty!")
            }

            println("UUID: $uuid")
            println("Name: $name")
            println("Email: $email")
            println("Password(hashed): ${passwordHash.hash}")
            println("Salt: ${passwordHash.salt}")

            "NICE TOKEN"
        }

}