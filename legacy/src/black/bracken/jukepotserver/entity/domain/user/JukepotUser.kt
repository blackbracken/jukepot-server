package black.bracken.jukepotserver.entity.domain.user

import org.joda.time.LocalDateTime
import java.util.*

data class JukepotUser(
    val uuid: UUID,
    val email: EmailAddress,
    val authenticationHash: AuthenticationHash,
    val name: UserName,
    val registeredAt: LocalDateTime
)