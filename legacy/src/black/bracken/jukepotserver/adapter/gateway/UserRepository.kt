package black.bracken.jukepotserver.adapter.gateway

import black.bracken.jukepotserver.entity.domain.user.AuthenticationHash
import black.bracken.jukepotserver.entity.domain.user.EmailAddress
import black.bracken.jukepotserver.entity.domain.user.JukepotUser
import black.bracken.jukepotserver.entity.domain.user.UserName
import black.bracken.jukepotserver.infrastructure.database.tables.User
import org.jetbrains.exposed.sql.transactions.transaction
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

class MariaUserRepository : UserRepository {

    override fun findUserById(uuid: UUID): JukepotUser? =
        transaction {
            val user = User.findById(uuid) ?: return@transaction null

            JukepotUser(
                uuid = uuid,
                email = EmailAddress(user.email) ?: return@transaction null,
                authenticationHash = AuthenticationHash(user.hashedPassword, user.passwordSalt),
                name = UserName(user.name) ?: return@transaction null,
                registeredAt = user.registeredAt.toLocalDateTime()
            )
        }

    override fun registerUser(
        email: EmailAddress,
        hashedPassword: String,
        passwordSalt: String,
        name: UserName,
        registeredAt: LocalDateTime
    ): JukepotUser? =
        transaction {
            User.new {
                this.email = email.text
                this.hashedPassword = hashedPassword
                this.passwordSalt = passwordSalt
                this.name = name.text
                this.registeredAt = registeredAt.toDateTime()
            }.toJukepotUser()
        }

    private fun User.toJukepotUser(): JukepotUser? {
        val uuid = id.value
        val email = EmailAddress(email) ?: return null
        val authenticationHash = AuthenticationHash(hashedPassword, passwordSalt)
        val name = UserName(name) ?: return null
        val registeredAt = registeredAt.toLocalDateTime() ?: return null

        return JukepotUser(uuid, email, authenticationHash, name, registeredAt)
    }

}