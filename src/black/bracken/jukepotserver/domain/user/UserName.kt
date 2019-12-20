package black.bracken.jukepotserver.domain.user

@Suppress("DataClassPrivateConstructor")
data class UserName private constructor(val text: String) {

    companion object {
        operator fun invoke(text: String): UserName? =
            when {
                text.isEmpty() || text.length > 32 -> null
                else -> UserName(text)
            }
    }

}