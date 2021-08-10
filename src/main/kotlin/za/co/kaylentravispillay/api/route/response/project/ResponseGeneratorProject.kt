package za.co.kaylentravispillay.api.route.response.project

import za.co.kaylentravispillay.api.data.project.model.Project
import za.co.kaylentravispillay.api.route.response.ResponseGeneratorResult

interface ResponseGeneratorProject {
    fun getProjectPageSuccessResponse(projects: List<Project>): ResponseGeneratorResult

    fun getNoProjectFound(username: String, page: String): ResponseGeneratorResult

    fun getFetchingProjectError(): ResponseGeneratorResult
}