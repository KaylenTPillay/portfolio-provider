package za.co.kaylentravispillay.api_v2.data.source.factory

import za.co.kaylentravispillay.api_v2.data.source.database.Database
import za.co.kaylentravispillay.api_v2.data.source.database.impl.DatabaseImpl

object SourceFactory {

    fun createDatabaseSource(testing: Boolean): Database {
        return DatabaseImpl
    }
}