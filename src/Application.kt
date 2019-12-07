package black.bracken.jukepotserver

import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.response.respondText
import io.ktor.routing.get
import io.ktor.routing.routing
import io.ktor.server.netty.EngineMain
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.insertIgnore
import org.jetbrains.exposed.sql.transactions.transaction
import kotlin.random.Random

fun main() = EngineMain.main(arrayOf())

object Numbers : Table() {
    val number = integer("number")
}

fun Application.module() {
    Database.connect("jdbc:mariadb://jukepot-mariadb/jukebox", "org.mariadb.jdbc.Driver", "user", "password")

    transaction {
        SchemaUtils.create(Numbers)
    }

    routing {
        get("/") {
            call.respondText("Hi.")
            transaction {
                Numbers.insertIgnore {
                    it[number] = Random.nextInt()
                }
            }
        }
    }
}