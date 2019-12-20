package black.bracken.jukepotserver.domain.user

@Suppress("DataClassPrivateConstructor")
data class Password private constructor(val text: String) {

    companion object {
        operator fun invoke(text: String): Password? =
            if (text.isValidPassword()) Password(text) else null

        private fun String.isValidPassword(): Boolean = Regex(
            "^"
                    + "(?=.*?[A-Za-z])" // アルファベット1文字以上
                    + "(?=.*?[0-9])" // 数字1文字以上
                    + "(?=.*?[#?!@\$%^&*-])" // #?!@$%^&*-を許容
                    + ".{8,64}" // 文字数: 8..64文字
                    + "$"
        ).matches(this)
    }

}