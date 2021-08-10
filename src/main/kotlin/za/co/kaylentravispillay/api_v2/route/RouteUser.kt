package za.co.kaylentravispillay.api_v2.route

import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import za.co.kaylentravispillay.api_v2.controller.ControllerUser
import za.co.kaylentravispillay.api_v2.data.model.DataModelUser

private const val USER_ROUTE = "/user"

class RouteUser(private val controller: ControllerUser) {

    fun apply(application: Application) {
        application.routing {
            applyGetUserRoute()
            applyPostUserRoute()
            applyPutUserRoute()
            applyDeleteUserRoute()
        }
    }

    private fun Routing.applyGetUserRoute() {
        get(USER_ROUTE) {
            val response = controller.getUserGetResponse(
                call.request.queryParameters["id"]
            )
            call.respond(response.first, response.second)
        }
    }

    private fun Routing.applyPostUserRoute() {
        post(USER_ROUTE) {
            val model = try {
                call.receive<DataModelUser>()
            } catch (exp: Exception) {
                null
            }
            val response = controller.getUserPostResponse(model)
            call.respond(response.first, response.second)
        }
    }

    private fun Routing.applyPutUserRoute() {
        put(USER_ROUTE) {
            val model = try {
                call.receive<DataModelUser>()
            } catch (exp: Exception) {
                null
            }
            val response = controller.getUserPutResponse(
                id = call.request.queryParameters["id"],
                model = model
            )
            call.respond(response.first, response.second)
        }
    }

    private fun Routing.applyDeleteUserRoute() {
        delete(USER_ROUTE) {
            val response = controller.getUserDeleteResponse(
                id = call.request.queryParameters["id"]
            )
            call.respond(response.first, response.second)
        }
    }
}