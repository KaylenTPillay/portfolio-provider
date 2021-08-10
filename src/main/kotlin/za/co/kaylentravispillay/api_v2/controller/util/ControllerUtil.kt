package za.co.kaylentravispillay.api_v2.controller.util

import io.ktor.http.*

typealias ControllerResponse = Pair<HttpStatusCode, Any>

fun getErrorResponseMessage(message: String): Map<String, String> {
    return mapOf(
        "message" to message
    )
}