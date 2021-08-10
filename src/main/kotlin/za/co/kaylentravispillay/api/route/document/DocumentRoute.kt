package za.co.kaylentravispillay.api.route.document

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import za.co.kaylentravispillay.api.data.document.repository.RepositoryDocument
import za.co.kaylentravispillay.api.route.PPRoute
import za.co.kaylentravispillay.api.route.document.path.RoutePathDocument
import za.co.kaylentravispillay.api.route.response.document.ResponseGeneratorDocument
import za.co.kaylentravispillay.api.route.response.respondWithResponseGeneratorResult

class DocumentRoute(
    private val repository: RepositoryDocument,
    private val path: RoutePathDocument,
    private val responseGenerator: ResponseGeneratorDocument
) : PPRoute {

    override fun applyRoute(application: Application) {
        application.routing {
            createPostDocumentRoute()
            createGetDocumentsForUserIdRoute()
            createGetUserDocumentByNameRoute()
        }
    }

    private fun Routing.createPostDocumentRoute() {
        post(path.POST_DOCUMENT_BY_ID) {
            call.parameters[path.USER_ID_KEY]?.let { id ->
                val multipart = call.receiveMultipart()
                val response = repository.uploadUserDocument(multipart, id)

                call.respondWithResponseGeneratorResult(
                    if (response) {
                        responseGenerator.getUnsuccessfulDocumentUploadResponse()
                    } else {
                        responseGenerator.getSuccessfulDocumentUploadResponse()
                    }
                )

            } ?: call.respondWithResponseGeneratorResult(
                responseGenerator.getUnsuccessfulDocumentUploadResponse()
            )
        }
    }

    private fun Routing.createGetDocumentsForUserIdRoute() {
        get(path.GET_USER_DOCUMENTS) {
            call.parameters[path.USER_ID_KEY]?.let { id ->
                repository.getUserDocumentsById(id)?.let { documents ->
                    call.respondWithResponseGeneratorResult(
                        responseGenerator.getUserDocumentsResponse(documents)
                    )
                } ?: call.respondWithResponseGeneratorResult(
                    responseGenerator.getFailedDocumentResponse()
                )
            } ?: call.respondWithResponseGeneratorResult(
                responseGenerator.getFailedDocumentResponse()
            )
        }
    }

    private fun Routing.createGetUserDocumentByNameRoute() {
        get(path.GET_USER_DOCUMENT_BY_FILE_NAME) {
            call.parameters[path.FILE_NAME_KEY]?.let { fileName ->
                val file = repository.getUserDocument(fileName)

                if (file != null) {
                    call.response.header(
                        name = "Content-Disposition",
                        value = "attachment: filename=\"${fileName}\""
                    )
                    call.respondFile(file)
                } else {
                    // TODO: Respond with error
                }
            }
        }
    }
}