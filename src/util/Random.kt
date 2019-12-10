package black.bracken.jukepotserver.util

import black.bracken.jukepotserver.ext.toText
import java.security.SecureRandom

object RandomGenerator {

    fun generateText(length: Int): String {
        val randomBytes = ByteArray(length / 2).apply {
            SecureRandom.getInstance("SHA1PRNG").nextBytes(this)
        }

        return randomBytes.toText()
    }

}