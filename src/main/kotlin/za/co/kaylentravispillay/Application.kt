package za.co.kaylentravispillay

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.jackson.*
import io.ktor.server.netty.*
import za.co.kaylentravispillay.api.plugins.document.configureDocumentPlugin
import za.co.kaylentravispillay.api.plugins.information.configureInformationPlugin
import za.co.kaylentravispillay.api.plugins.project.configureProjectPlugin
import za.co.kaylentravispillay.api_v2.data.source.factory.SourceFactory
import za.co.kaylentravispillay.api_v2.route.factory.RouteFactory

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module(testing: Boolean = false) {
    //configureAPI(testing)
    configureAPIV2(testing)
}

private fun Application.configureAPI(testing: Boolean) {
    install(ContentNegotiation) {
        jackson()
    }

    configureInformationPlugin(testing)
    configureProjectPlugin(testing)
    configureDocumentPlugin(testing)
}

private fun Application.configureAPIV2(testing: Boolean) {
    install(ContentNegotiation) {
        jackson()
    }
    SourceFactory.createDatabaseSource(testing).initialise(environment.config)

    RouteFactory.createUserRoute(testing).apply(this)
    RouteFactory.createAboutRoute(testing).apply(this)
}