package za.co.kaylentravispillay.api.source.database.factory

import za.co.kaylentravispillay.api.source.database.PPDatabase
import za.co.kaylentravispillay.api.source.database.impl.PPDataBaseLocalImpl
import za.co.kaylentravispillay.api.source.factory.PPSourceFactory

object PPDatabaseFactoryLocal : PPSourceFactory<PPDatabase> {

    override fun create(): PPDatabase {
        return PPDataBaseLocalImpl()
    }
}