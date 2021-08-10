package za.co.kaylentravispillay.api.route.information

import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.routing.*
import za.co.kaylentravispillay.api.data.information.model.Information
import za.co.kaylentravispillay.api.data.information.repository.RepositoryInformation
import za.co.kaylentravispillay.api.route.PPRoute
import za.co.kaylentravispillay.api.route.information.path.RoutePathInformation
import za.co.kaylentravispillay.api.route.response.information.ResponseGeneratorInformation
import za.co.kaylentravispillay.api.route.response.respondWithResponseGeneratorResult

class InformationRoute(
    private val repository: RepositoryInformation,
    private val path: RoutePathInformation,
    private val responseGenerator: ResponseGeneratorInformation
) : PPRoute {

    override fun applyRoute(application: Application) {
        application.routing {
            createGetInformationByIdRoute()
            createPostNewInformationRoute()
        }
    }

    private fun Routing.createGetInformationByIdRoute() {
        get(path.GET_INFORMATION_BY_ID) {
            call.parameters[path.INFORMATION_ID_KEY]?.let { id ->
                repository.getInformationById(id)?.let { information ->
                    call.respondWithResponseGeneratorResult(
                        responseGenerator.getSuccessfulInformationResponse(information)
                    )
                } ?: call.respondWithResponseGeneratorResult(
                    responseGenerator.getErrorNoInformationResponse()
                )
            }
        }
    }

    private fun Routing.createPostNewInformationRoute() {
        post(path.POST_CREATE_NEW_INFORMATION) {
            try {
                handleNewInformation(call)
            } catch (exp: Exception) {
                handleFailedNewInformationCreation(exp, call)
            }
        }
    }

    private suspend fun handleNewInformation(call: ApplicationCall) {
        val information = call.receive<Information>()

        val capturedInformation = repository.createNewInformation(information)

        call.respondWithResponseGeneratorResult(
            responseGenerator.getSuccessfulInformationResponse(capturedInformation)
        )
    }

    private suspend fun handleFailedNewInformationCreation(exp: Exception, call: ApplicationCall) {
        when (exp) {
            is ContentTransformationException, is UnrecognizedPropertyException -> {
                call.respondWithResponseGeneratorResult(
                    responseGenerator.getErrorUnableToCreateInformation()
                )
            }
            else -> throw exp
        }
    }
}