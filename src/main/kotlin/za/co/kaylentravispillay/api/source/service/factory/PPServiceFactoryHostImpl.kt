package za.co.kaylentravispillay.api.source.service.factory

import za.co.kaylentravispillay.api.source.factory.PPSourceFactory
import za.co.kaylentravispillay.api.source.service.PPService
import za.co.kaylentravispillay.api.source.service.impl.PPServiceHostImpl

object PPServiceFactoryHostImpl : PPSourceFactory<PPService> {

    override fun create(): PPService {
        return PPServiceHostImpl()
    }
}