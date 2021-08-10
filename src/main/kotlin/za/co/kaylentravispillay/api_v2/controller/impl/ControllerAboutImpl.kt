package za.co.kaylentravispillay.api_v2.controller.impl

import io.ktor.http.*
import za.co.kaylentravispillay.api_v2.controller.ControllerAbout
import za.co.kaylentravispillay.api_v2.controller.util.ControllerResponse
import za.co.kaylentravispillay.api_v2.controller.util.getErrorResponseMessage
import za.co.kaylentravispillay.api_v2.data.model.DataModelAboutSection
import za.co.kaylentravispillay.api_v2.data.repository.RepositoryAbout
import za.co.kaylentravispillay.api_v2.data.repository.RepositoryUser

class ControllerAboutImpl(
    private val repositoryUser: RepositoryUser,
    private val repositoryAbout: RepositoryAbout
) : ControllerAbout {

    override fun getAboutGetResponse(usedId: String?): ControllerResponse {
        if (usedId == null) return ControllerResponse(
            HttpStatusCode.BadRequest,
            getErrorResponseMessage("No Id provided")
        )

        val user = repositoryUser.getUser(usedId) ?: ControllerResponse(
            HttpStatusCode.InternalServerError,
            getErrorResponseMessage("Unable to fetch user about data for id $usedId")
        )

        val sections = repositoryAbout.getSections(usedId)

        return ControllerResponse(
            HttpStatusCode.OK,
            mapOf(
                "profile" to user,
                "sections" to sections
            )
        )
    }

    override fun getAboutPostResponse(userId: String?, model: DataModelAboutSection?): ControllerResponse {
        if (userId == null || model == null) return ControllerResponse(
            HttpStatusCode.BadRequest,
            getErrorResponseMessage("No Id or section model provided")
        )

        repositoryAbout.addSection(userId, model)

        return ControllerResponse(HttpStatusCode.OK, String())
    }

    override fun getAboutPutResponse(userId: String?, model: DataModelAboutSection?): ControllerResponse {
        if (userId == null || model == null || model.id.isBlank()) return ControllerResponse(
            HttpStatusCode.BadRequest,
            getErrorResponseMessage("No Id or section model provided")
        )

        repositoryAbout.updateSection(userId, model)

        return ControllerResponse(HttpStatusCode.OK, String())
    }

    override fun getAboutDeleteResponse(userId: String?, model: DataModelAboutSection?): ControllerResponse {
        if (userId == null || model == null || model.id.isBlank()) return ControllerResponse(
            HttpStatusCode.BadRequest,
            getErrorResponseMessage("No Id or section model provided")
        )

        repositoryAbout.removeSection(userId, model)

        return ControllerResponse(HttpStatusCode.OK, String())
    }
}