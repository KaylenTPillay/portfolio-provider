package za.co.kaylentravispillay.api.route.response.project.impl

import io.ktor.http.*
import za.co.kaylentravispillay.api.data.project.model.Project
import za.co.kaylentravispillay.api.route.response.ResponseGeneratorBase
import za.co.kaylentravispillay.api.route.response.ResponseGeneratorResult
import za.co.kaylentravispillay.api.route.response.project.ResponseGeneratorProject

object ResponseGeneratorProjectImpl : ResponseGeneratorProject {

    override fun getProjectPageSuccessResponse(projects: List<Project>): ResponseGeneratorResult {
        val responseValue = mapOf(
            "projects" to projects
        )

        val responseCode = HttpStatusCode.OK

        return Pair(responseCode, responseValue)
    }

    override fun getNoProjectFound(username: String, page: String): ResponseGeneratorResult {
        val responseValue = ResponseGeneratorBase.getErrorResponse(
            errorCode = "NO_PROJECTS_FOUND",
            errorMessage = "No projects found for $username on page $page"
        )
        val responseCode = HttpStatusCode.BadRequest

        return Pair(responseCode, responseValue)
    }

    override fun getFetchingProjectError(): ResponseGeneratorResult {
        val responseValue = ResponseGeneratorBase.getErrorResponse(
            errorCode = "FAILED_PROJECTS_RETRIEVAL",
            errorMessage = "Unable to fetch projects for user"
        )

        val responseCode = HttpStatusCode.InternalServerError

        return Pair(responseCode, responseValue)
    }
}