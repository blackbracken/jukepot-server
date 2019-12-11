package black.bracken.jukepotserver.util

import black.bracken.jukepotserver.ext.nextBytesText
import black.bracken.jukepotserver.ext.toText
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import kotlin.random.Random

object PasswordHashGenerator {
    private const val ALGORITHM = "PBKDF2WithHmacSHA256"
    private const val STRETCHING_COUNT = 1024
    private const val KEY_LENGTH = 256

    fun generate(password: String, salt: String): PasswordHash? {
        val factory = SecretKeyFactory.getInstance(ALGORITHM)
        val keySpec = PBEKeySpec(password.toCharArray(), salt.toByteArray(), STRETCHING_COUNT, KEY_LENGTH)
        val secretKey = factory.generateSecret(keySpec)

        return secretKey.encoded?.toText()?.let { hash -> PasswordHash(hash, salt) }
    }

    fun generate(password: String, saltLength: Int): PasswordHash? =
        generate(password, Random.nextBytesText(saltLength))

}

data class PasswordHash(val hash: String, val salt: String)