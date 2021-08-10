package za.co.kaylentravispillay.api.source.storage.impl

import io.ktor.http.content.*
import za.co.kaylentravispillay.api.source.storage.PPStorage
import java.io.File

class PPStorageLocalImpl : PPStorage {

    private val baseFileStoragePath = "/Users/ktp/IdeaProjects/portfolio-provider/src/main/resources/uploads"

    override suspend fun storeDocument(partData: PartData, filePrefix: String): String? {
        val documentName = try {
            val processedName = if (partData is PartData.FileItem) {
                val name = partData.originalFileName ?: return null
                val qualifiedName = "$filePrefix-$name"
                val file = File("$baseFileStoragePath/$qualifiedName")

                partData.streamProvider().use { inputStream ->
                    file.outputStream().buffered().use {
                        inputStream.copyTo(it)
                    }
                }

                qualifiedName
            } else {
                null
            }

            partData.dispose()
            processedName
        } catch (exp: Exception) {
            null
        }

        return documentName
    }

    override suspend fun getDocument(fileSuffix: String): File? {
        val file = File("$baseFileStoragePath/$fileSuffix")
        return if (file.exists()) { file } else { null }
    }
}