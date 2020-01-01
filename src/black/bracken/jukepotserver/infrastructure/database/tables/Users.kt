package black.bracken.jukepotserver.infrastructure.database.tables

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.UUIDTable
import java.util.*

object Users : UUIDTable("users") {
    val name = varchar("name", 64)
    val email = varchar("email", 64).uniqueIndex()
    val hashedPassword = varchar("hashed_password", 64)
    val passwordSalt = varchar("password_salt", 32)
    val registeredAt = datetime("registered_at")
}

class User(uuid: EntityID<UUID>) : UUIDEntity(uuid) {
    companion object : UUIDEntityClass<User>(Users)

    val name by Users.name
    val email by Users.email
    val hashedPassword by Users.hashedPassword
    val passwordSalt by Users.passwordSalt
    val registeredAt by Users.registeredAt
}