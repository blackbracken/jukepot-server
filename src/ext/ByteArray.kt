package black.bracken.jukepotserver.ext

fun ByteArray.toText(): String = this.joinToString("") { String.format("%02x", it) }