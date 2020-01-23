package black.bracken.jukepotserver.entity.domain.user

interface UserName {
    val text: String

    companion object {
        operator fun invoke(text: String): UserName? =
            when {
                text.isEmpty() || text.length > 32 -> null
                else -> Concrete(text)
            }
    }

    private data class Concrete(override val text: String) : UserName
}