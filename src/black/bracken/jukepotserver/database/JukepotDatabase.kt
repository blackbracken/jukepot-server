package black.bracken.jukepotserver.database

import black.bracken.jukepotserver.database.tables.Users
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException
import java.util.*

object JukepotDatabase {
    private const val TRIALS = 2 * 60

    fun initialize(host: String, database: String, user: String, password: String) {
        val url = "jdbc:mariadb://$host/$database"

        fun getConnectionOrNull(): Connection? =
            try {
                DriverManager.getConnection(url, Properties().apply {
                    put("user", user)
                    put("password", password)
                })
            } catch (ignored: SQLException) {
                null
            }

        // TODO: consider using Either
        fun tryConnectBlocking(trials: Int): Connection? =
            (0 until trials).asSequence()
                .map { getConnectionOrNull() ?: Thread.sleep(1_000) }
                .filterIsInstance<Connection>()
                .firstOrNull()

        Database.connect(getNewConnection = {
            tryConnectBlocking(TRIALS) ?: throw IllegalAccessException("Couldn't connect to black.bracken.jukepotserver.database!")
        })

        transaction {
            SchemaUtils.create(Users)
        }
    }

}