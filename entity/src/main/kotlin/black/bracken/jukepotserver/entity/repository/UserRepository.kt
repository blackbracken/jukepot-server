package black.bracken.jukepotserver.entity.repository

import black.bracken.jukepotserver.entity.EmailAddress
import black.bracken.jukepotserver.entity.JukepotUser
import black.bracken.jukepotserver.entity.UserName
import org.joda.time.LocalDateTime
import java.util.*

interface UserRepository {

    fun findUserById(uuid: UUID): JukepotUser?

    fun registerUser(
        email: EmailAddress,
        hashedPassword: String,
        passwordSalt: String,
        name: UserName,
        registeredAt: LocalDateTime
    ): JukepotUser?

}