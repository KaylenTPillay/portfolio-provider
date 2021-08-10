package za.co.kaylentravispillay.api_v2.data.source.database.impl

import io.ktor.config.*
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import za.co.kaylentravispillay.api_v2.data.source.database.Database
import za.co.kaylentravispillay.api_v2.data.source.database.table.TableAboutMe
import za.co.kaylentravispillay.api_v2.data.source.database.table.TableUser
import za.co.kaylentravispillay.api_v2.data.source.database.table.transaction.TableTransaction
import za.co.kaylentravispillay.api_v2.data.source.database.table.transaction.impl.TableTransactionAboutMe
import za.co.kaylentravispillay.api_v2.data.source.database.table.transaction.impl.TableTransactionUser
import za.co.kaylentravispillay.api_v2.data.source.database.table.transaction.type.TableTransactionType
import org.jetbrains.exposed.sql.Database as ExposedDatabase
import java.util.concurrent.atomic.AtomicBoolean

object DatabaseImpl : Database {
    private var isInitialised: AtomicBoolean = AtomicBoolean(false)

    override fun initialise(config: ApplicationConfig) {
        if (!isInitialised.getAndSet(true)) {
            val url = config.propertyOrNull("ktor.dbSetting.url")?.getString()
            val driver = config.propertyOrNull("ktor.dbSetting.driver")?.getString()
            val user = config.propertyOrNull("ktor.dbSetting.user")?.getString()
            val password = config.propertyOrNull("ktor.dbSetting.password")?.getString()

            connectDatabase(url, driver, user, password)
            initialiseTables()
        }
    }

    override fun getTableTransaction(type: TableTransactionType): TableTransaction {
        if (!isInitialised.get()) throw IllegalStateException("Database needs to be initialised")
        return when (type) {
            TableTransactionType.USER -> TableTransactionUser()
            TableTransactionType.ABOUT_ME -> TableTransactionAboutMe()
        }
    }

    private fun connectDatabase(url: String?, driver: String?, user: String?, password: String?) {
        if (url != null && driver != null && user != null && password != null) {
            ExposedDatabase.connect(
                url = url,
                driver = driver,
                user = user,
                password = password
            )
        }
    }

    private fun initialiseTables() {
        transaction { SchemaUtils.create(TableUser, TableAboutMe) }
    }
}