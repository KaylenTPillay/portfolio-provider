package za.co.kaylentravispillay.api_v2.controller.impl

import io.ktor.http.*
import za.co.kaylentravispillay.api_v2.controller.ControllerUser
import za.co.kaylentravispillay.api_v2.controller.util.ControllerResponse
import za.co.kaylentravispillay.api_v2.controller.util.getErrorResponseMessage
import za.co.kaylentravispillay.api_v2.data.model.DataModelUser
import za.co.kaylentravispillay.api_v2.data.repository.RepositoryUser

class ControllerUserImpl(private val repository: RepositoryUser) : ControllerUser {

    override fun getUserGetResponse(id: String?): ControllerResponse {
        if (id == null) return ControllerResponse(
            HttpStatusCode.BadRequest,
            getErrorResponseMessage("No id provided")
        )

        val userDataModel = repository.getUser(id) ?: return ControllerResponse(
            HttpStatusCode.NotFound,
            getErrorResponseMessage("Unable to get user information for id $id")
        )

        return ControllerResponse(HttpStatusCode.OK, userDataModel)
    }

    override fun getUserPostResponse(model: DataModelUser?): ControllerResponse {
        if (model == null) return ControllerResponse(
            HttpStatusCode.BadRequest,
            getErrorResponseMessage("No user information body provided")
        )
        if (model.name.isBlank() || model.surname.isBlank()) return ControllerResponse(
            HttpStatusCode.BadRequest,
            getErrorResponseMessage("User name and surname are required properties")
        )

        repository.addUser(
            name = model.name,
            surname = model.surname,
            image = model.image
        )

        return ControllerResponse(HttpStatusCode.OK, String())
    }

    override fun getUserPutResponse(id: String?, model: DataModelUser?): ControllerResponse {
        if (model == null || id == null) return ControllerResponse(
            HttpStatusCode.BadRequest,
            getErrorResponseMessage("Id and User information body is required")
        )

        if (model.name.isNotBlank()) {
            repository.updateUserName(id, model.name)
        }

        if (model.surname.isNotBlank()) {
            repository.updateUserSurname(id, model.surname)
        }

        if (model.image.isNotBlank()) {
            repository.updateUserImage(id, model.image)
        }

        return ControllerResponse(HttpStatusCode.OK, String())
    }

    override fun getUserDeleteResponse(id: String?): ControllerResponse {
        if (id == null) return ControllerResponse(
            HttpStatusCode.BadRequest,
            getErrorResponseMessage("No id provided for delete action")
        )

        repository.deleteUser(id)
        return Pair(HttpStatusCode.OK, String())
    }
}