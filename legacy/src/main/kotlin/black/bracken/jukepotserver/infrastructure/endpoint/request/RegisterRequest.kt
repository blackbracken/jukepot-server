package black.bracken.jukepotserver.infrastructure.endpoint.request

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String
)