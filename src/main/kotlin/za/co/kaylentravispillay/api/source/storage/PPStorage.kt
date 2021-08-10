package za.co.kaylentravispillay.api.source.storage

import io.ktor.http.content.*
import java.io.File

interface PPStorage {
    suspend fun storeDocument(partData: PartData, filePrefix: String): String?

    suspend fun getDocument(fileSuffix: String): File?
}