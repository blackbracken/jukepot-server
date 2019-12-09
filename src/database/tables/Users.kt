package black.bracken.jukepotserver.database.tables

import org.jetbrains.exposed.sql.Table

object Users : Table("users") {
    val uuid = uuid("uuid").primaryKey()
    val name = varchar("name", 64)
    val email = varchar("email", 256).nullable()
    val hashedPassword = varchar("hashed_password", 64)
    val passwordSalt = varchar("password_salt", 16)
    val registeredAt = datetime("registered_at")
}