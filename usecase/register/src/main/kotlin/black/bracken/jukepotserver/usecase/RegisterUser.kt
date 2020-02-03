package black.bracken.jukepotserver.usecase

import arrow.core.Either
import arrow.core.extensions.fx
import arrow.core.rightIfNotNull
import black.bracken.jukepotserver.InvalidResponse
import black.bracken.jukepotserver.UseCase
import black.bracken.jukepotserver.entity.EmailAddress
import black.bracken.jukepotserver.entity.PasswordText
import black.bracken.jukepotserver.entity.UserName
import black.bracken.jukepotserver.entity.repository.UserRepository
import black.bracken.jukepotserver.ext.toText
import org.joda.time.LocalDateTime
import java.security.SecureRandom
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class RegisterUser(
    private val userRepository: UserRepository
) : UseCase<RegisterUser.Input, Either<InvalidResponse, UUID>> {

    data class Input(val email: String, val password: String, val userName: String)

    override fun invoke(inputTransferObject: Input): Either<InvalidResponse, UUID> =
        Either.fx {
            val (email) = EmailAddress(inputTransferObject.email)
                .rightIfNotNull { InvalidResponse("Address is invalid!") }
            val (password) = PasswordText(inputTransferObject.password)
                .rightIfNotNull { InvalidResponse("Password is invalid!") }
            val (name) = UserName(inputTransferObject.userName)
                .rightIfNotNull { InvalidResponse("UserName is invalid!") }

            val (hashedPassword, salt) = password.text.hash()

            val (registeredUser) = userRepository.registerUser(email, hashedPassword, salt, name, LocalDateTime.now())
                .rightIfNotNull { InvalidResponse("Something happened!") }

            registeredUser.uuid
        }

    // TODO: type return value
    private fun String.hash(): Pair<String, String> {
        val algorithm = "PBKDF2WithHmacSHA256"
        val stretchingCount = 1024
        val keyLength = 256

        val keyFactory = SecretKeyFactory.getInstance(algorithm)
        val salt = Array<Byte>(32) { 0 }.toByteArray().apply {
            SecureRandom().nextBytes(this)
        }
        val keySpec = PBEKeySpec(this.toCharArray(), salt, stretchingCount, keyLength)
        val secretKey = keyFactory.generateSecret(keySpec)

        return secretKey.encoded.toText()
            .let { hashedPassword -> hashedPassword to salt.toText() }
    }

}