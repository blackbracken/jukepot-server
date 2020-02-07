package black.bracken.jukepotserver.database.tables

import org.jetbrains.exposed.dao.EntityID
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.UUIDTable
import java.util.*

object Tokens : UUIDTable("tokens") {
    val token = varchar("token", 96).uniqueIndex()
    val user = reference("user", Users)
    val createdAt = datetime("created_at")
}

class Token(uuid: EntityID<UUID>) : UUIDEntity(uuid) {
    companion object : UUIDEntityClass<Token>(Tokens)

    var token by Tokens.token
    var user by Tokens.user
    var createdAt by Tokens.createdAt
}