package black.bracken.jukepotserver.ext

fun Array<Byte>.toText(): String = this.joinToString("") { byte -> String.format("%02x", byte) }
fun ByteArray.toText(): String = toTypedArray().toText()