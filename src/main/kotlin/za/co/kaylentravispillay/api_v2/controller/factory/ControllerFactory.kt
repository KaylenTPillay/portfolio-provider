package za.co.kaylentravispillay.api_v2.controller.factory

import za.co.kaylentravispillay.api_v2.controller.ControllerAbout
import za.co.kaylentravispillay.api_v2.controller.ControllerUser
import za.co.kaylentravispillay.api_v2.controller.impl.ControllerAboutImpl
import za.co.kaylentravispillay.api_v2.controller.impl.ControllerUserImpl
import za.co.kaylentravispillay.api_v2.data.repository.factory.RepositoryFactory

object ControllerFactory {

    fun createUserController(testing: Boolean): ControllerUser {
        return ControllerUserImpl(
            repository = RepositoryFactory.createUserRepository(testing)
        )
    }

    fun createAboutController(testing: Boolean): ControllerAbout {
        return ControllerAboutImpl(
            repositoryUser = RepositoryFactory.createUserRepository(testing),
            repositoryAbout = RepositoryFactory.createAboutRepository(testing)
        )
    }
}