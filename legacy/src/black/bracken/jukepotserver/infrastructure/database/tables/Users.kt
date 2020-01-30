package black.bracken.jukepotserver.infrastructure.database.tables

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.UUIDTable
import java.util.*

object Users : UUIDTable("users") {
    val email = varchar("email", 64).uniqueIndex()
    val hashedPassword = varchar("hashed_password", 64)
    val passwordSalt = varchar("password_salt", 64)
    val name = varchar("name", 64)
    val registeredAt = datetime("registered_at")
}

class User(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<User>(Users)

    var email by Users.email
    var hashedPassword by Users.hashedPassword
    var passwordSalt by Users.passwordSalt
    var name by Users.name
    var registeredAt by Users.registeredAt
}