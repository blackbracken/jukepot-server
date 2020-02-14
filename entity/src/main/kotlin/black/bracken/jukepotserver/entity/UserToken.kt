package black.bracken.jukepotserver.entity

import java.time.ZonedDateTime
import java.util.*

data class UserToken(
    val uuid: UUID,
    val userId: UUID,
    val createdAt: ZonedDateTime
)