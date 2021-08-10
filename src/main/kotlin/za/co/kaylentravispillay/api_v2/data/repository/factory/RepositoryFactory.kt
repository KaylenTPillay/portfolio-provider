package za.co.kaylentravispillay.api_v2.data.repository.factory

import za.co.kaylentravispillay.api_v2.data.repository.RepositoryAbout
import za.co.kaylentravispillay.api_v2.data.repository.RepositoryUser
import za.co.kaylentravispillay.api_v2.data.repository.impl.RepositoryAboutImpl
import za.co.kaylentravispillay.api_v2.data.repository.impl.RepositoryUserImpl
import za.co.kaylentravispillay.api_v2.data.source.factory.SourceFactory
import javax.xml.transform.Source

object RepositoryFactory {

    fun createUserRepository(testing: Boolean): RepositoryUser {
        return RepositoryUserImpl(
            database = SourceFactory.createDatabaseSource(testing)
        )
    }

    fun createAboutRepository(testing: Boolean): RepositoryAbout {
        return RepositoryAboutImpl(
            database = SourceFactory.createDatabaseSource(testing)
        )
    }
}