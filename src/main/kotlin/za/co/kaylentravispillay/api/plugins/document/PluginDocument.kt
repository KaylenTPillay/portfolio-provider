package za.co.kaylentravispillay.api.plugins.document

import io.ktor.application.*
import za.co.kaylentravispillay.api.data.document.repository.RepositoryDocument
import za.co.kaylentravispillay.api.data.document.repository.impl.RepositoryDocumentImpl
import za.co.kaylentravispillay.api.route.document.DocumentRoute
import za.co.kaylentravispillay.api.route.document.path.RoutePathDocument
import za.co.kaylentravispillay.api.route.document.path.impl.RoutePathDocumentImpl
import za.co.kaylentravispillay.api.route.response.document.ResponseGeneratorDocument
import za.co.kaylentravispillay.api.route.response.document.impl.ResponseGeneratorDocumentImpl
import za.co.kaylentravispillay.api.source.database.impl.PPDataBaseLocalImpl
import za.co.kaylentravispillay.api.source.storage.impl.PPStorageLocalImpl

fun Application.configureDocumentPlugin(testing: Boolean) {
    val repository = createDocumentRepository(testing)
    val path = createRoutePathDocument()
    val responseGenerator = createResponseGeneratorDocument()

    if (repository != null) {
        DocumentRoute(
            repository = repository,
            path = path,
            responseGenerator = responseGenerator
        ).applyRoute(this)
    }
}

private fun Application.createDocumentRepository(testing: Boolean): RepositoryDocument? {
    if (testing) return RepositoryDocumentImpl(
        sourceStorage = PPStorageLocalImpl(),
        sourceDatabase = PPDataBaseLocalImpl()
    )

    return when(environment.config.propertyOrNull("ktor.environment")?.getString()) {
        "Production", "Development" -> RepositoryDocumentImpl(
            sourceStorage = PPStorageLocalImpl(),
            sourceDatabase = PPDataBaseLocalImpl()
        )
        else -> null
    }
}

private fun createRoutePathDocument(): RoutePathDocument {
    return RoutePathDocumentImpl
}

private fun createResponseGeneratorDocument(): ResponseGeneratorDocument {
    return ResponseGeneratorDocumentImpl()
}