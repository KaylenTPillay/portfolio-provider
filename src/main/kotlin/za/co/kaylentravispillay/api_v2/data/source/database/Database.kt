package za.co.kaylentravispillay.api_v2.data.source.database

import io.ktor.config.*
import za.co.kaylentravispillay.api_v2.data.source.database.table.transaction.TableTransaction
import za.co.kaylentravispillay.api_v2.data.source.database.table.transaction.type.TableTransactionType

interface Database {
    fun initialise(config: ApplicationConfig)

    fun getTableTransaction(type: TableTransactionType) : TableTransaction
}