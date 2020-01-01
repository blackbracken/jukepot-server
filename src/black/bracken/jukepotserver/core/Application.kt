package black.bracken.jukepotserver.core

import black.bracken.jukepotserver.infrastructure.database.JukepotDatabase
import black.bracken.jukepotserver.infrastructure.route.register
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.ContentNegotiation
import io.ktor.gson.gson
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.EngineMain
import io.ktor.server.netty.Netty
import io.ktor.util.KtorExperimentalAPI
import org.koin.ktor.ext.Koin

private const val ENV_DATABASE = "jukepot.database"

fun main() = EngineMain.main(arrayOf())

@KtorExperimentalAPI
fun Application.module() {
    with(environment.config) {
        val dbHost = propertyOrNull("$ENV_DATABASE.host")?.getString() ?: "localhost"
        val dbDatabase = propertyOrNull("$ENV_DATABASE.database")?.getString() ?: "jukepot"
        val dbUser = property("$ENV_DATABASE.user").getString()
        val dbPassword = property("$ENV_DATABASE.password").getString()

        JukepotDatabase.initialize(dbHost, dbDatabase, dbUser, dbPassword)
    }

    embeddedServer(Netty) {
        routing {
            install(ContentNegotiation) {
                gson {
                    setPrettyPrinting()
                }
            }
            install(Koin) {
                modules(appModule)
            }

            register()
        }
    }.start(wait = true)
}