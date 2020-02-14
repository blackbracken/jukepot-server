package black.bracken.jukepotserver.database.repositories

import black.bracken.jukepotserver.database.tables.Token
import black.bracken.jukepotserver.database.tables.User
import black.bracken.jukepotserver.entity.UserToken
import black.bracken.jukepotserver.entity.repository.TokenRepository
import black.bracken.jukepotserver.ext.toJavaDateTime
import black.bracken.jukepotserver.ext.toJodaDateTime
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.ZonedDateTime
import java.util.*

class MariaTokenRepository : TokenRepository {

    override fun generateToken(userId: UUID): UserToken? =
        transaction {
            val now = ZonedDateTime.now()
            val userFromId = User.findById(userId) ?: return@transaction null

            Token.new {
                user = userFromId
                createdAt = now.toJodaDateTime()
            }.toUserToken()
        }

    override fun revokeToken(tokenId: UUID): Boolean =
        transaction {
            val token = Token.findById(tokenId) ?: return@transaction false

            token.delete()
            true
        }

    override fun findTokenById(tokenId: UUID): UserToken? =
        transaction {
            Token.findById(tokenId)?.toUserToken()
        }

    private fun Token.toUserToken(): UserToken =
        UserToken(uuid, user.uuid, createdAt.toJavaDateTime())

}