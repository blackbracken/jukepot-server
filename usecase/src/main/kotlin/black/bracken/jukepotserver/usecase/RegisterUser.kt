package black.bracken.jukepotserver.usecase

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import black.bracken.jukepotserver.ErrorResponse
import black.bracken.jukepotserver.InternalError
import black.bracken.jukepotserver.InvalidParameter
import black.bracken.jukepotserver.UseCase
import black.bracken.jukepotserver.entity.EmailAddress
import black.bracken.jukepotserver.entity.PasswordText
import black.bracken.jukepotserver.entity.UserName
import black.bracken.jukepotserver.entity.UserToken
import black.bracken.jukepotserver.entity.repository.TokenRepository
import black.bracken.jukepotserver.entity.repository.UserRepository
import black.bracken.jukepotserver.ext.toText
import java.security.SecureRandom
import java.time.ZonedDateTime
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class RegisterUser(
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository
) : UseCase<RegisterUser.Input, Either<ErrorResponse, UserToken>> {

    data class Input(val email: String, val password: String, val userName: String)

    override fun invoke(inputTransferObject: Input): Either<ErrorResponse, UserToken> {
        val email = EmailAddress(inputTransferObject.email)
            ?: return InvalidParameter("Address").left()

        val password = PasswordText(inputTransferObject.password)
            ?: return InvalidParameter("Password").left()

        val name = UserName(inputTransferObject.userName)
            ?: return InvalidParameter("UserName").left()

        val (hashedPassword, salt) = password.text.hash()
        val registeredUser = userRepository.registerUser(email, hashedPassword, salt, name, ZonedDateTime.now())
            ?: return InternalError().left()

        return tokenRepository.generateToken(registeredUser.uuid)?.right()
            ?: InternalError().left()
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