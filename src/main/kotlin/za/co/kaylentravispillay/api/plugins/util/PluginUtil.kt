package za.co.kaylentravispillay.api.plugins.util

import io.ktor.config.*
import za.co.kaylentravispillay.api.data.information.repository.RepositoryInformation
import za.co.kaylentravispillay.api.data.information.repository.impl.RepositoryInformationImpl
import za.co.kaylentravispillay.api.source.database.factory.PPDatabaseFactoryHost
import za.co.kaylentravispillay.api.source.database.factory.PPDatabaseFactoryLocal
import za.co.kaylentravispillay.api.source.database.setting.DBSetting

object PluginUtil {
    fun createInformationRepository(config: ApplicationConfig, testing: Boolean): RepositoryInformation? {
        if (testing) return RepositoryInformationImpl(PPDatabaseFactoryLocal.create())

        return when (config.propertyOrNull("ktor.environment")?.getString()) {
            "Development" -> {
                RepositoryInformationImpl(PPDatabaseFactoryLocal.create())
            }
            "Production" -> {
                DBSetting.initialise(config)
                RepositoryInformationImpl(PPDatabaseFactoryHost.create())
            }
            else -> null
        }
    }
}