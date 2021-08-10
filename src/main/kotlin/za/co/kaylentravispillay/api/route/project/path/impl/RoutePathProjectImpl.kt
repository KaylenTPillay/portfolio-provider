package za.co.kaylentravispillay.api.route.project.path.impl

import za.co.kaylentravispillay.api.route.project.path.RoutePathProject

object RoutePathProjectImpl : RoutePathProject {
    private const val BASE_PROJECT_PATH = "/projects"

    override val INFORMATION_ID_KEY: String = "id"
    override val PROJECT_PAGE_KEY: String = "page"
    override val GET_PROJECTS_BY_ID: String = "${BASE_PROJECT_PATH}/{${INFORMATION_ID_KEY}}/{${PROJECT_PAGE_KEY}}"
}