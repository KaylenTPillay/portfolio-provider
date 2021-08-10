package za.co.kaylentravispillay.api.source.database

import za.co.kaylentravispillay.api.data.information.model.Information

interface PPDatabase {
    suspend fun getInformationById(id: String): Information?

    suspend fun createInformation(information: Information): Information?

    suspend fun addUserDocumentById(id: String, documentName: String): Boolean

    suspend fun getUserDocumentsById(id: String): List<String>?
}