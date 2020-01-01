package black.bracken.jukepotserver.entity.domain.user

import org.apache.commons.validator.routines.EmailValidator

interface EmailAddress {
    val text: String

    companion object {
        fun of(text: String): EmailAddress? =
            if (EmailValidator.getInstance(true).isValid(text)) Concrete(text) else null
    }

    private data class Concrete(override val text: String) : EmailAddress
}