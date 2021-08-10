package za.co.kaylentravispillay.api.route.response.document

import za.co.kaylentravispillay.api.data.document.model.UserDocument
import za.co.kaylentravispillay.api.route.response.ResponseGeneratorResult

interface ResponseGeneratorDocument {
    fun getSuccessfulDocumentUploadResponse(): ResponseGeneratorResult

    fun getUnsuccessfulDocumentUploadResponse(): ResponseGeneratorResult

    fun getUserDocumentsResponse(documents: List<UserDocument>): ResponseGeneratorResult

    fun getFailedDocumentResponse(): ResponseGeneratorResult
}