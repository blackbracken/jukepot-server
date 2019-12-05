package black.bracken.jukepotserver

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.netty.EngineMain

fun main() = EngineMain.main(arrayOf())

fun Application.module() {
    routing {
        get("/") {
            call.respondText("Hi.")
        }
    }
}