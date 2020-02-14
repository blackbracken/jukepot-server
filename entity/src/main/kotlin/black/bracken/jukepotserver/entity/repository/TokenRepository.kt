package black.bracken.jukepotserver.entity.repository

import black.bracken.jukepotserver.entity.UserToken
import java.util.*

interface TokenRepository {

    fun generateToken(userId: UUID): UserToken?

    fun revokeToken(tokenId: UUID): Boolean

    fun findTokenById(tokenId: UUID): UserToken?

}