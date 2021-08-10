package za.co.kaylentravispillay.api.plugins.information

import io.ktor.application.*
import za.co.kaylentravispillay.api.plugins.util.PluginUtil
import za.co.kaylentravispillay.api.route.information.InformationRoute
import za.co.kaylentravispillay.api.route.information.path.RoutePathInformation
import za.co.kaylentravispillay.api.route.information.path.impl.RoutePathInformationImpl
import za.co.kaylentravispillay.api.route.response.information.ResponseGeneratorInformation
import za.co.kaylentravispillay.api.route.response.information.impl.ResponseGeneratorInformationImpl

fun Application.configureInformationPlugin(testing: Boolean = false) {
    val repository = PluginUtil.createInformationRepository(
        environment.config,
        testing
    )
    val path = createInformationRoutePath(testing)
    val responseGenerator = createInformationResponseGenerator(testing)

    if (repository != null &&
        path != null &&
        responseGenerator != null
    ) {
        InformationRoute(
            repository = repository,
            path = path,
            responseGenerator = responseGenerator
        ).applyRoute(this)
    }
}

private fun Application.createInformationRoutePath(testing: Boolean): RoutePathInformation? {
    if (testing) return RoutePathInformationImpl

    return when (environment.config.propertyOrNull("ktor.environment")?.getString()) {
        "Development", "Production" -> RoutePathInformationImpl
        else -> null
    }
}

private fun Application.createInformationResponseGenerator(testing: Boolean): ResponseGeneratorInformation? {
    if (testing) return ResponseGeneratorInformationImpl()

    return when (environment.config.propertyOrNull("ktor.environment")?.getString()) {
        "Development", "Production" -> ResponseGeneratorInformationImpl()
        else -> null
    }
}