package za.co.kaylentravispillay.api.source.database.setting

import io.ktor.config.*
import org.jetbrains.exposed.sql.*
import java.util.concurrent.atomic.AtomicBoolean

object DBSetting {
    private var isInitialised: AtomicBoolean = AtomicBoolean(false)

    fun initialise(config: ApplicationConfig) {
//        if (!isInitialised.getAndSet(true)) {
//            val url = config.propertyOrNull("ktor.dbSetting.url")?.getString()
//            val driver = config.propertyOrNull("ktor.dbSetting.driver")?.getString()
//            val user = config.propertyOrNull("ktor.dbSetting.user")?.getString()
//            val password = config.propertyOrNull("ktor.dbSetting.password")?.getString()
//
//            if (url != null &&
//                driver != null &&
//                user != null &&
//                password != null
//            ) {
//                Database.connect(
//                    url = url,
//                    driver = driver,
//                    user = user,
//                    password = password
//                )
//            }
//        }
    }

    object Tables {
        object InformationCollection : Table(name = "InformationCollection_v2") {
            val id = integer(name = "id").autoIncrement()
            val first_name = varchar(name = "first_name", length = 255)
            val last_name = varchar(name = "last_name", length = 255)
            val github_username = varchar(name = "github_username", length = 255)

            override val primaryKey: PrimaryKey = PrimaryKey(id)
        }
    }
}