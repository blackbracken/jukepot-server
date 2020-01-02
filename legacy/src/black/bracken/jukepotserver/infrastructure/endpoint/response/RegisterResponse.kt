package black.bracken.jukepotserver.infrastructure.endpoint.response

sealed class RegisterResponse

data class Success(val token: String) : RegisterResponse()

data class Error(val error: String) : RegisterResponse()