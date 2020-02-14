package black.bracken.jukepotserver.entity

import java.time.ZonedDateTime
import java.util.*

data class JukepotUser(
    val uuid: UUID,
    val email: EmailAddress,
    val authenticationHash: AuthenticationHash,
    val name: UserName,
    val registeredAt: ZonedDateTime
)