package black.bracken.jukepotserver.routes.register

import black.bracken.jukepotserver.model.Register
import black.bracken.jukepotserver.util.PasswordHashGenerator
import io.ktor.routing.Route
import io.ktor.routing.post
import java.util.*

private const val SALT_LENGTH = 32

fun Route.register() {
    post<Register>("/register") { register ->
        val uuid = UUID.randomUUID().toString()
        val passwordHash = PasswordHashGenerator.generate(register.password, SALT_LENGTH) ?: return@post

        // debugging
        println("UUID(${uuid.length}): $uuid")
        println("Name(${register.name.length}): ${register.name}")
        println("Email(${register.email.length}): ${register.email}")
        println("Password(${passwordHash.hash.length}): ${passwordHash.hash}")
        println("Salt(${passwordHash.salt.length}): ${passwordHash.salt}")
    }
}

