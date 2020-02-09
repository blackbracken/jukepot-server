package black.bracken.jukepotserver

import black.bracken.jukepotserver.database.MariaDatabase
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
        val dbHost = propertyOrNull("$ENV_DATABASE.host")?.getString()
            ?: throw IllegalStateException("DB_HOST must be set!")
        val dbDatabase = propertyOrNull("$ENV_DATABASE.database")?.getString()
            ?: throw IllegalStateException("DB_DATABASE must be set!")
        val dbUser = propertyOrNull("$ENV_DATABASE.user")?.getString()
            ?: throw IllegalStateException("DB_USER must be set!")
        val dbPassword = propertyOrNull("$ENV_DATABASE.password")?.getString()
            ?: throw IllegalStateException("DB_PASSWORD must be set!")

        MariaDatabase.initialize(dbHost, dbDatabase, dbUser, dbPassword)
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
            // TODO: install StatusPages to catch unhandled exceptions

            user()
        }
    }.start(wait = true)
}