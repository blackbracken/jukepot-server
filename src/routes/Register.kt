package black.bracken.jukepotserver.routes

import black.bracken.jukepotserver.ext.toText
import black.bracken.jukepotserver.model.Register
import black.bracken.jukepotserver.util.RandomGenerator
import io.ktor.routing.Route
import io.ktor.routing.post
import java.util.*
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

private const val ALGORITHM = "PBKDF2WithHmacSHA256"

fun Route.register() {
    post<Register>("/register") { register ->
        val uuid = UUID.randomUUID().toString()
        val passwordSalt = RandomGenerator.generateText(32)

        val factory = SecretKeyFactory.getInstance(ALGORITHM)
        val keySpec = PBEKeySpec(register.password.toCharArray(), passwordSalt.toByteArray(), 1024, 256)
        val secretKey = factory.generateSecret(keySpec)
        val hashedPassword = secretKey.encoded?.toText() ?: return@post

        // debugging
        println("UUID(${uuid.length}): $uuid")
        println("Name(${register.name.length}): ${register.name}")
        println("Email(${register.email.length}): ${register.email}")
        println("Password(${hashedPassword.length}): $hashedPassword")
        println("Salt(${passwordSalt.length}): $passwordSalt")
    }
}

