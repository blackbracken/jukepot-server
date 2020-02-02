package black.bracken.jukepotserver.service.user.register

import arrow.core.Either
import arrow.core.extensions.fx
import arrow.core.rightIfNotNull
import black.bracken.jukepotserver.adapter.gateway.UserRepository
import black.bracken.jukepotserver.entity.EmailAddress
import black.bracken.jukepotserver.entity.PasswordText
import black.bracken.jukepotserver.entity.UserName
import black.bracken.jukepotserver.ext.toText
import black.bracken.jukepotserver.util.InvalidResponse
import org.joda.time.LocalDateTime
import java.security.SecureRandom
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

class UserRegisterInteractor(private val userRepository: UserRepository) : UserRegisterUsecase {

    override fun invoke(inputTransferObject: UserRegisterUsecase.Input): Either<InvalidResponse, UUID> =
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