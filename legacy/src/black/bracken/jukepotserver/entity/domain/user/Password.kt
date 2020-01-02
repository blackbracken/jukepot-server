package black.bracken.jukepotserver.entity.domain.user

interface Password {
    val text: String

    companion object {
        fun of(text: String): Password? {
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

    data class Concrete(override val text: String) : Password
}