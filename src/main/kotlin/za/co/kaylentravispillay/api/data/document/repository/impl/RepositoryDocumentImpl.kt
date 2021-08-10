package za.co.kaylentravispillay.api.data.document.repository.impl

import io.ktor.http.content.*
import za.co.kaylentravispillay.api.data.document.model.UserDocument
import za.co.kaylentravispillay.api.data.document.repository.RepositoryDocument
import za.co.kaylentravispillay.api.source.database.PPDatabase
import za.co.kaylentravispillay.api.source.storage.PPStorage
import java.io.File

class RepositoryDocumentImpl(
    private val sourceStorage: PPStorage,
    private val sourceDatabase: PPDatabase
) : RepositoryDocument {

    override suspend fun uploadUserDocument(documentPathData: MultiPartData, userId: String): Boolean {
        var hasFailedUpload = false
        documentPathData.forEachPart { part ->
            sourceStorage.storeDocument(part, userId)?.let { fileName ->
                val hasSavedFileName = sourceDatabase.addUserDocumentById(userId, fileName)
                if (!hasSavedFileName) {
                    hasFailedUpload = true
                }
            } ?: run {
                hasFailedUpload = true
            }
        }

        return hasFailedUpload
    }

    override suspend fun getUserDocumentsById(id: String): List<UserDocument>? {
        return sourceDatabase.getUserDocumentsById(id)?.mapNotNull { fileSuffix ->
            val file = sourceStorage.getDocument(fileSuffix = fileSuffix)

            val type = file?.extension
            val size = file?.totalSpace?.toString()
            val name = file?.name

            if (type != null || size != null || name != null) {
                UserDocument(name, type, size)
            } else {
                null
            }
        }
    }

    override suspend fun getUserDocument(fileName: String): File? {
        return sourceStorage.getDocument(fileSuffix = fileName)
    }
}