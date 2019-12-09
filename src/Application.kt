package black.bracken.jukepotserver

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.http.ContentType
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.EngineMain
import io.ktor.server.netty.Netty
import io.ktor.util.KtorExperimentalAPI
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insertIgnore
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.*
import kotlin.random.Random

private const val ENV_DATABASE = "jukepot.database"

fun main() = EngineMain.main(arrayOf())

@KtorExperimentalAPI
fun Application.module() {
    with(environment.config) {
        val dbHost = propertyOrNull("$ENV_DATABASE.host")?.getString() ?: "localhost"
        val dbDatabase = propertyOrNull("$ENV_DATABASE.database")?.getString() ?: "jukepot"
        val dbUser = property("$ENV_DATABASE.user").getString()
        val dbPassword = property("$ENV_DATABASE.password").getString()

        val url = "jdbc:mariadb://$dbHost/$dbDatabase"
        while (getConnectionOrNull(url, dbUser, dbPassword)?.also(Connection::close) == null) {
            Thread.sleep(1_000)
        }

        Database.connect(url, "org.mariadb.jdbc.Driver", dbUser, dbPassword)
    }

    transaction {
        SchemaUtils.create(Numbers)
    }

    embeddedServer(Netty) {
        routing {
            get("/") {
                call.respondText("Hi.", ContentType.Text.Plain)

                transaction {
                    Numbers.insertIgnore {
                        it[number] = Random.nextInt()
                    }
                }
            }
        }
    }.start(wait = true)
}

private fun getConnectionOrNull(url: String, user: String, password: String): Connection? =
    try {
        DriverManager.getConnection(url, Properties().apply {
            put("user", user)
            put("password", password)
        })
    } catch (ignored: SQLException) {
        null
    }

object Numbers : Table() {
    val number = integer("number")
}