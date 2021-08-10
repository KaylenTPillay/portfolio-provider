package za.co.kaylentravispillay.api.route.response.information.impl

import io.ktor.http.*
import za.co.kaylentravispillay.api.data.information.model.Information
import za.co.kaylentravispillay.api.route.response.ResponseGeneratorBase
import za.co.kaylentravispillay.api.route.response.ResponseGeneratorResult
import za.co.kaylentravispillay.api.route.response.information.ResponseGeneratorInformation

class ResponseGeneratorInformationImpl : ResponseGeneratorInformation {

    override fun getSuccessfulInformationResponse(information: Information): ResponseGeneratorResult {
        val responseValue = mapOf(
            "user_information" to information
        )
        val responseCode = HttpStatusCode.OK

        return Pair(responseCode, responseValue)
    }

    override fun getErrorNoInformationResponse(): ResponseGeneratorResult {
        val responseValue = ResponseGeneratorBase.getErrorResponse(
            errorCode = "NO_INFORMATION_FOUND",
            errorMessage = "No user found for provided id"
        )
        val responseCode = HttpStatusCode.BadRequest

        return Pair(responseCode, responseValue)
    }

    override fun getErrorUnableToCreateInformation(): ResponseGeneratorResult {
        val responseValue = ResponseGeneratorBase.getErrorResponse(
            errorCode = "UNABLE_TO_CREATE",
            errorMessage = "We are unable to create new information. Please try again later"
        )
        val responseCode = HttpStatusCode.BadRequest

        return Pair(responseCode, responseValue)
    }
}