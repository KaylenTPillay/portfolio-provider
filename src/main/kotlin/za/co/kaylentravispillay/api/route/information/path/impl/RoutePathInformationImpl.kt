package za.co.kaylentravispillay.api.route.information.path.impl

import za.co.kaylentravispillay.api.route.information.path.RoutePathInformation

object RoutePathInformationImpl :  RoutePathInformation {
    private const val BASE_INFORMATION_PATH = "/information"

    override val INFORMATION_ID_KEY: String = "id"
    override val GET_INFORMATION_BY_ID: String = "${BASE_INFORMATION_PATH}/{${INFORMATION_ID_KEY}}"
    override val POST_CREATE_NEW_INFORMATION: String = BASE_INFORMATION_PATH
}