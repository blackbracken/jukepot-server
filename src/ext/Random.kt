package black.bracken.jukepotserver.ext

import kotlin.random.Random

fun Random.Default.nextBytesText(textLength: Int): String = nextBytes(textLength / 2).toText()