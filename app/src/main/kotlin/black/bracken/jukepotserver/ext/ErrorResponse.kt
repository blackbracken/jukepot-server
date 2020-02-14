package black.bracken.jukepotserver.ext

import black.bracken.jukepotserver.ErrorResponse
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.json

internal fun ErrorResponse.toJsonObject(): JsonObject = json {
    "reason" to reason
}