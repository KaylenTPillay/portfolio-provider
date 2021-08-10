package za.co.kaylentravispillay.api.data.information.repository.impl

import za.co.kaylentravispillay.api.data.information.model.Information
import za.co.kaylentravispillay.api.data.information.repository.RepositoryInformation
import za.co.kaylentravispillay.api.source.database.PPDatabase

class RepositoryInformationImpl(private val database: PPDatabase) : RepositoryInformation {

    override suspend fun getInformationById(id: String): Information? {
        return database.getInformationById(id)
    }

    override suspend fun createNewInformation(information: Information): Information {
        return database.createInformation(information) ?: information
    }
}