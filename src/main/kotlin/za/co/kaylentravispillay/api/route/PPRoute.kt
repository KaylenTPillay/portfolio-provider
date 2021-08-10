package za.co.kaylentravispillay.api.route

import io.ktor.application.*

interface PPRoute {
    fun applyRoute(application: Application)
}