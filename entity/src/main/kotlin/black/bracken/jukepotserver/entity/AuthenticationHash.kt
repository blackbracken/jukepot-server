package black.bracken.jukepotserver.entity

data class AuthenticationHash(
    val hashedPassword: String,
    val salt: String
)