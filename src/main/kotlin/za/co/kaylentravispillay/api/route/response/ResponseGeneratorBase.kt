package za.co.kaylentravispillay.api.route.response

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*

typealias ResponseGeneratorResult = Pair<HttpStatusCode, Any>

object ResponseGeneratorBase {

    fun getErrorResponse(errorCode: String, errorMessage: String): Map<String, Any> {
        return mapOf(
            "code" to errorCode,
            "message" to errorMessage
        )
    }
}

suspend fun ApplicationCall.respondWithResponseGeneratorResult(result: ResponseGeneratorResult) {
    val code = result.first
    val response = result.second

    respond(code, response)
}