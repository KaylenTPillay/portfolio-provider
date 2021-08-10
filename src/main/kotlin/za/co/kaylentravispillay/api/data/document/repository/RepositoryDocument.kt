package za.co.kaylentravispillay.api.data.document.repository

import io.ktor.http.content.*
import za.co.kaylentravispillay.api.data.document.model.UserDocument
import java.io.File

interface RepositoryDocument {
    suspend fun uploadUserDocument(documentPathData: MultiPartData, userId: String): Boolean

    suspend fun getUserDocumentsById(id: String): List<UserDocument>?

    suspend fun getUserDocument(fileName: String): File?
}