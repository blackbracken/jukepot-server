package black.bracken.jukepotserver.database.repositories

import black.bracken.jukepotserver.database.tables.User
import black.bracken.jukepotserver.entity.AuthenticationHash
import black.bracken.jukepotserver.entity.EmailAddress
import black.bracken.jukepotserver.entity.JukepotUser
import black.bracken.jukepotserver.entity.UserName
import black.bracken.jukepotserver.entity.repository.UserRepository
import org.jetbrains.exposed.sql.transactions.transaction
import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import java.time.ZoneId
import java.util.*

private typealias JodaDateTime = DateTime
private typealias JavaDateTime = java.time.ZonedDateTime

class MariaUserRepository : UserRepository {

    override fun findUserById(uuid: UUID): JukepotUser? =
        transaction {
            val user = User.findById(uuid) ?: return@transaction null

            JukepotUser(
                uuid = uuid,
                email = EmailAddress(user.email) ?: return@transaction null,
                authenticationHash = AuthenticationHash(user.hashedPassword, user.passwordSalt),
                name = UserName(user.name) ?: return@transaction null,
                registeredAt = user.registeredAt.toJavaDateTime()
            )
        }

    override fun registerUser(
        email: EmailAddress,
        hashedPassword: String,
        passwordSalt: String,
        name: UserName,
        registeredAt: JavaDateTime
    ): JukepotUser? =
        transaction {
            User.new {
                this.email = email.text
                this.hashedPassword = hashedPassword
                this.passwordSalt = passwordSalt
                this.name = name.text
                this.registeredAt = registeredAt.toJodaDateTime()
            }.toJukepotUser()
        }

    private fun User.toJukepotUser(): JukepotUser? {
        val uuid = id.value
        val email = EmailAddress(email) ?: return null
        val authenticationHash = AuthenticationHash(hashedPassword, passwordSalt)
        val name = UserName(name) ?: return null
        val registeredAt = registeredAt

        return JukepotUser(uuid, email, authenticationHash, name, registeredAt.toJavaDateTime())
    }

    private fun JodaDateTime.toJavaDateTime(): JavaDateTime =
        run {
            JavaDateTime.of(
                year,
                monthOfYear,
                dayOfMonth,
                hourOfDay,
                minuteOfHour,
                secondOfMinute,
                0,
                ZoneId.systemDefault()
            )
        }

    private fun JavaDateTime.toJodaDateTime(): JodaDateTime =
        run {
            JodaDateTime(year, monthValue, dayOfMonth, hour, minute, second, DateTimeZone.getDefault())
        }

}