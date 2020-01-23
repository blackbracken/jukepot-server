package black.bracken.jukepotserver.entity.domain.user

data class AuthenticationHash(
    val hashedPassword: String,
    val salt: String
)