package black.bracken.jukepotserver.ext

import black.bracken.jukepotserver.ErrorResponse

internal fun ErrorResponse.toJsonItem() = ErrorResponseItem(this.reason)

internal data class ErrorResponseItem(val reason: String)