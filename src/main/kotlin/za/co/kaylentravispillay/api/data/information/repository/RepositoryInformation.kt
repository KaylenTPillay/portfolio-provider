package za.co.kaylentravispillay.api.data.information.repository

import za.co.kaylentravispillay.api.data.information.model.Information

interface RepositoryInformation {
    suspend fun getInformationById(id: String): Information?

    suspend fun createNewInformation(information: Information): Information
}