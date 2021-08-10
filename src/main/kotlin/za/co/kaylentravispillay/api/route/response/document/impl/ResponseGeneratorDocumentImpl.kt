package za.co.kaylentravispillay.api.route.response.document.impl

import io.ktor.http.*
import za.co.kaylentravispillay.api.data.document.model.UserDocument
import za.co.kaylentravispillay.api.route.response.ResponseGeneratorBase
import za.co.kaylentravispillay.api.route.response.ResponseGeneratorResult
import za.co.kaylentravispillay.api.route.response.document.ResponseGeneratorDocument

class ResponseGeneratorDocumentImpl : ResponseGeneratorDocument {
    override fun getSuccessfulDocumentUploadResponse(): ResponseGeneratorResult {
        return Pair(HttpStatusCode.NoContent, Any())
    }

    override fun getUnsuccessfulDocumentUploadResponse(): ResponseGeneratorResult {
        val responseError = ResponseGeneratorBase.getErrorResponse(
            errorCode = "UNABLE_TO_PROCESS_DOCUMENT",
            errorMessage = "Something went wrong whilst trying to process your file. Please try again"
        )

        val responseCode = HttpStatusCode.InternalServerError

        return Pair(responseCode, responseError)
    }

    override fun getUserDocumentsResponse(documents: List<UserDocument>): ResponseGeneratorResult {
        val responseValue = mapOf("documents" to documents)
        val responseCode = HttpStatusCode.OK

        return Pair(responseCode, responseValue)
    }

    override fun getFailedDocumentResponse(): ResponseGeneratorResult {
        val responseError = ResponseGeneratorBase.getErrorResponse(
            errorCode = "UNABLE_TO_FETCH_DOCUMENTS",
            errorMessage = "Something went wrong whilst trying to fetch your file. Please try again"
        )

        val responseCode = HttpStatusCode.InternalServerError

        return Pair(responseCode, responseError)
    }
}