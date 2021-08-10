package za.co.kaylentravispillay.api.source.database.factory

import za.co.kaylentravispillay.api.source.database.PPDatabase
import za.co.kaylentravispillay.api.source.database.impl.PPDatabaseHostImpl
import za.co.kaylentravispillay.api.source.factory.PPSourceFactory

object PPDatabaseFactoryHost : PPSourceFactory<PPDatabase> {

    override fun create(): PPDatabase {
        return PPDatabaseHostImpl()
    }
}