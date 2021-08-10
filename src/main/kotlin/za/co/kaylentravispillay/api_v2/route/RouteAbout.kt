package za.co.kaylentravispillay.api_v2.route

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import za.co.kaylentravispillay.api_v2.controller.ControllerAbout
import za.co.kaylentravispillay.api_v2.data.model.DataModelAboutSection

private const val ABOUT_ROUTE = "/about"

class RouteAbout(private val controller: ControllerAbout) {

    fun apply(application: Application) {
        application.routing {
            applyGetAboutRoute()
            applyPostAboutRoute()
            applyPutAboutRoute()
            applyDeleteAboutRoute()
        }
    }

    private fun Routing.applyGetAboutRoute() {
        get(ABOUT_ROUTE) {
            val response = controller.getAboutGetResponse(
                call.request.queryParameters["id"]
            )

            call.respond(response.first, response.second)
        }
    }

    private fun Routing.applyPostAboutRoute() {
        post(ABOUT_ROUTE) {
            val model = call.receiveOrNull<DataModelAboutSection>()

            val response = controller.getAboutPostResponse(
                call.request.queryParameters["id"],
                model
            )

            call.respond(response.first, response.second)
        }
    }

    private fun Routing.applyPutAboutRoute() {
        put(ABOUT_ROUTE) {
            val model = call.receiveOrNull<DataModelAboutSection>()

            val response = controller.getAboutPutResponse(
                call.request.queryParameters["id"],
                model
            )

            call.respond(response.first, response.second)
        }
    }

    private fun Routing.applyDeleteAboutRoute() {
        delete(ABOUT_ROUTE) {
            val model = call.receiveOrNull<DataModelAboutSection>()

            val response = controller.getAboutDeleteResponse(
                call.request.queryParameters["id"],
                model
            )

            call.respond(response.first, response.second)
        }
    }
}