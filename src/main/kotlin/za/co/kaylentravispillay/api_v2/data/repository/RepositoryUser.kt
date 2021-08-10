package za.co.kaylentravispillay.api_v2.data.repository

import za.co.kaylentravispillay.api_v2.data.model.DataModelUser

interface RepositoryUser {
    fun addUser(name: String, surname: String, image: String)

    fun getUser(id: String): DataModelUser?

    fun updateUserName(id: String, name: String): Boolean

    fun updateUserSurname(id: String, surname: String): Boolean

    fun updateUserImage(id: String, image: String): Boolean

    fun deleteUser(id: String): Boolean
}