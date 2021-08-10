package za.co.kaylentravispillay.api.source.database.impl

import za.co.kaylentravispillay.api.data.document.model.UserDocument
import za.co.kaylentravispillay.api.data.information.model.Information
import za.co.kaylentravispillay.api.source.database.PPDatabase

class PPDataBaseLocalImpl : PPDatabase {

    private val mLocalInformationCollection: MutableList<Information> = mutableListOf(
        Information(
            id = "0",
            name = "Test-name",
            surname = "Test-surname",
            githubUsername = "Test-username"
        )
    )

    private val mLocalDocumentCollection: MutableList<Pair<String, String>> = mutableListOf()

    override suspend fun getInformationById(id: String): Information? {
        return mLocalInformationCollection.find { information -> information.id == id }
    }

    override suspend fun createInformation(information: Information): Information {
        val capturedInformation = information.copy(
            id = mLocalInformationCollection.size.toString()
        )
        mLocalInformationCollection.add(capturedInformation)
        return capturedInformation
    }

    override suspend fun addUserDocumentById(id: String, documentName: String): Boolean {
        return mLocalDocumentCollection.add(Pair(id, documentName))
    }

    override suspend fun getUserDocumentsById(id: String): List<String> {
        return mLocalDocumentCollection.filter { it.first == id }
            .map { it.second }
    }
}