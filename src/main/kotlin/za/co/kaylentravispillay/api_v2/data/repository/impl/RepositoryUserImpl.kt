package za.co.kaylentravispillay.api_v2.data.repository.impl

import za.co.kaylentravispillay.api_v2.data.model.DataModelUser
import za.co.kaylentravispillay.api_v2.data.model.mapping.mapToDataModel
import za.co.kaylentravispillay.api_v2.data.repository.RepositoryUser
import za.co.kaylentravispillay.api_v2.data.source.database.Database
import za.co.kaylentravispillay.api_v2.data.source.database.table.transaction.impl.TableTransactionUser
import za.co.kaylentravispillay.api_v2.data.source.database.table.transaction.type.TableTransactionType

class RepositoryUserImpl(database: Database) : RepositoryUser {

    private val mUserTableTransaction: TableTransactionUser = database.getTableTransaction(
        TableTransactionType.USER
    ) as TableTransactionUser

    override fun addUser(name: String, surname: String, image: String) {
        mUserTableTransaction.createUser(name, surname, image)
    }

    override fun getUser(id: String): DataModelUser? {
        return id.toIntOrNull()?.let { idInt ->
            mUserTableTransaction.getUser(idInt)?.mapToDataModel()
        }
    }

    override fun updateUserName(id: String, name: String): Boolean {
        return id.toIntOrNull()?.let { idInt ->
            mUserTableTransaction.updateUserName(idInt, name)
            true
        } ?: false
    }

    override fun updateUserSurname(id: String, surname: String): Boolean {
        return id.toIntOrNull()?.let { idInt ->
            mUserTableTransaction.updateUserSurname(idInt, surname)
            true
        } ?: false
    }

    override fun updateUserImage(id: String, image: String): Boolean {
        return id.toIntOrNull()?.let { idInt ->
            mUserTableTransaction.updateUserImage(idInt, image)
            true
        } ?: false
    }

    override fun deleteUser(id: String): Boolean {
        return id.toIntOrNull()?.let { idInt ->
            mUserTableTransaction.deleteUser(idInt)
            true
        } ?: false
    }
}