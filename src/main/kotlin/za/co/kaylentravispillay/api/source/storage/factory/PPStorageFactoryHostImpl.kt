package za.co.kaylentravispillay.api.source.storage.factory

import za.co.kaylentravispillay.api.source.factory.PPSourceFactory
import za.co.kaylentravispillay.api.source.storage.PPStorage
import za.co.kaylentravispillay.api.source.storage.impl.PPStorageLocalImpl

object PPStorageFactoryHostImpl : PPSourceFactory<PPStorage> {

    override fun create(): PPStorage {
        return PPStorageLocalImpl()
    }
}