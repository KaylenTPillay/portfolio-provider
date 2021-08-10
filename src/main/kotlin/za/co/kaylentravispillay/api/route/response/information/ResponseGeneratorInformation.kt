package za.co.kaylentravispillay.api.route.response.information

import za.co.kaylentravispillay.api.data.information.model.Information
import za.co.kaylentravispillay.api.route.response.ResponseGeneratorResult

interface ResponseGeneratorInformation {
    fun getSuccessfulInformationResponse(information: Information): ResponseGeneratorResult

    fun getErrorNoInformationResponse(): ResponseGeneratorResult

    fun getErrorUnableToCreateInformation(): ResponseGeneratorResult
}