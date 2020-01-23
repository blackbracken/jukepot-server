package black.bracken.jukepotserver.entity.domain.user

interface PasswordText {
    val text: String

    companion object {
        operator fun invoke(text: String): PasswordText? {
            val passwordRegex = Regex(
                "^"
                        + "(?=.*[A-Z])" // 大文字が含まれなければならない
                        + "(?=.*[a-z])" // 小文字が含まれなければならない
                        + "(?=.*[0-9])" // 数字が含まれなければならない
                        + "[A-Za-z0-9@\\-\\$]" // 許容される文字
                        + "{8,64}" // 文字数は8..64である
                        + "$"
            )

            return if (passwordRegex.matches(text)) Concrete(text) else null
        }
    }

    private data class Concrete(override val text: String) : PasswordText
}