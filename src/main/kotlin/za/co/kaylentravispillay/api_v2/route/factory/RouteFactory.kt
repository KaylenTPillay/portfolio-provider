package za.co.kaylentravispillay.api_v2.route.factory

import za.co.kaylentravispillay.api_v2.controller.factory.ControllerFactory
import za.co.kaylentravispillay.api_v2.route.RouteAbout
import za.co.kaylentravispillay.api_v2.route.RouteUser

object RouteFactory{

    fun createUserRoute(testing: Boolean): RouteUser {
        return RouteUser(
            controller = ControllerFactory.createUserController(testing)
        )
    }

    fun createAboutRoute(testing: Boolean): RouteAbout {
        return RouteAbout(
            controller = ControllerFactory.createAboutController(testing)
        )
    }
}