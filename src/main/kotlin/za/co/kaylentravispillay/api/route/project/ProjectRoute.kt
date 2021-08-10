package za.co.kaylentravispillay.api.route.project

import io.ktor.application.*
import io.ktor.routing.*
import za.co.kaylentravispillay.api.data.information.repository.RepositoryInformation
import za.co.kaylentravispillay.api.data.project.repository.RepositoryProject
import za.co.kaylentravispillay.api.route.PPRoute
import za.co.kaylentravispillay.api.route.project.path.RoutePathProject
import za.co.kaylentravispillay.api.route.response.project.ResponseGeneratorProject
import za.co.kaylentravispillay.api.route.response.respondWithResponseGeneratorResult

class ProjectRoute(
    private val repositoryProject: RepositoryProject,
    private val repositoryInformation: RepositoryInformation,
    private val path: RoutePathProject,
    private val responseGenerator: ResponseGeneratorProject
) : PPRoute {

    override fun applyRoute(application: Application) {
        application.routing {
            createGetProjectsByUsernameRoute()
        }
    }

    private fun Routing.createGetProjectsByUsernameRoute() {
        get(path.GET_PROJECTS_BY_ID) {
            val id = call.parameters[path.INFORMATION_ID_KEY]
            val page = call.parameters[path.PROJECT_PAGE_KEY]
            if (id != null && page != null) {
                repositoryInformation.getInformationById(id)?.let { information ->
                    val projectListForPage = repositoryProject.getProjectsByUsernameForPage(
                        information.githubUsername,
                        page
                    )

                    call.respondWithResponseGeneratorResult(
                        if (projectListForPage != null) {
                            responseGenerator.getProjectPageSuccessResponse(projectListForPage)
                        } else {
                            responseGenerator.getNoProjectFound(
                                username = information.githubUsername,
                                page = page
                            )
                        }
                    )
                } ?: call.respondWithResponseGeneratorResult(
                    responseGenerator.getFetchingProjectError()
                )
            } else {
                call.respondWithResponseGeneratorResult(
                    responseGenerator.getFetchingProjectError()
                )
            }
        }
    }
}