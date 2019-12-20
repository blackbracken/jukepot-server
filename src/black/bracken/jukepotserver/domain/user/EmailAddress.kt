package black.bracken.jukepotserver.domain.user

import org.apache.commons.validator.routines.EmailValidator

@Suppress("DataClassPrivateConstructor")
data class EmailAddress private constructor(val text: String) {

    companion object {
        operator fun invoke(text: String): EmailAddress? =
            if (EmailValidator.getInstance(true).isValid(text)) EmailAddress(text) else null
    }

}