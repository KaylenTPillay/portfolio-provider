package za.co.kaylentravispillay.api.plugins.project

import io.ktor.application.*
import za.co.kaylentravispillay.api.data.project.repository.RepositoryProject
import za.co.kaylentravispillay.api.data.project.repository.impl.RepositoryProjectImpl
import za.co.kaylentravispillay.api.plugins.util.PluginUtil
import za.co.kaylentravispillay.api.route.project.ProjectRoute
import za.co.kaylentravispillay.api.route.project.path.RoutePathProject
import za.co.kaylentravispillay.api.route.project.path.impl.RoutePathProjectImpl
import za.co.kaylentravispillay.api.route.response.project.ResponseGeneratorProject
import za.co.kaylentravispillay.api.route.response.project.impl.ResponseGeneratorProjectImpl
import za.co.kaylentravispillay.api.source.service.factory.PPServiceFactoryHostImpl
import za.co.kaylentravispillay.api.source.service.factory.PPServiceFactoryLocalImpl

fun Application.configureProjectPlugin(testing: Boolean = false) {
    val repositoryProject = createProjectRouteRepository(testing)
    val repositoryInformation = PluginUtil.createInformationRepository(
        environment.config,
        testing
    )
    val path = createProjectRoutePath(testing)
    val responseGenerator = createProjectResponseGenerator(testing)

    if (repositoryProject != null &&
        repositoryInformation != null &&
        path != null &&
        responseGenerator != null
    ) {
        ProjectRoute(
            repositoryProject = repositoryProject,
            repositoryInformation = repositoryInformation,
            path = path,
            responseGenerator = responseGenerator
        ).applyRoute(this)
    }
}

private fun Application.createProjectRouteRepository(testing: Boolean): RepositoryProject? {
    if (testing) return RepositoryProjectImpl(PPServiceFactoryLocalImpl.create())

    return when (environment.config.propertyOrNull("ktor.environment")?.getString()) {
        "Development" -> {
            RepositoryProjectImpl(PPServiceFactoryLocalImpl.create())
        }
        "Production" -> {
            RepositoryProjectImpl(PPServiceFactoryHostImpl.create())
        }
        else -> null
    }
}

private fun Application.createProjectRoutePath(testing: Boolean): RoutePathProject? {
    if (testing) return RoutePathProjectImpl

    return when (environment.config.propertyOrNull("ktor.environment")?.getString()) {
        "Development", "Production" -> {
            RoutePathProjectImpl
        }
        else -> null
    }
}

private fun Application.createProjectResponseGenerator(testing: Boolean): ResponseGeneratorProject? {
    if (testing) return ResponseGeneratorProjectImpl

    return when (environment.config.propertyOrNull("ktor.environment")?.getString()) {
        "Development", "Production" -> ResponseGeneratorProjectImpl
        else -> null
    }
}