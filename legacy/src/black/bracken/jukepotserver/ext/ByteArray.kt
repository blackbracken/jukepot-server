package black.bracken.jukepotserver.ext

fun ByteArray.toText(): String = this.joinToString("") { byte -> String.format("%02x", byte) }