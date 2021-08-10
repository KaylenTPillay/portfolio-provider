package za.co.kaylentravispillay.api.source.database.impl

import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import za.co.kaylentravispillay.api.data.information.model.Information
import za.co.kaylentravispillay.api.source.database.PPDatabase
import za.co.kaylentravispillay.api.source.database.setting.DBSetting

class PPDatabaseHostImpl : PPDatabase {

    override suspend fun getInformationById(id: String): Information? {
        return id.toIntOrNull()?.let { idInt ->
            transaction {
                val resultRow = DBSetting.Tables.InformationCollection.select {
                    DBSetting.Tables.InformationCollection.id eq idInt
                }.singleOrNull()

                return@transaction if (resultRow != null) {
                    Information(
                        id = resultRow[DBSetting.Tables.InformationCollection.id].toString(),
                        name = resultRow[DBSetting.Tables.InformationCollection.first_name],
                        surname = resultRow[DBSetting.Tables.InformationCollection.last_name],
                        githubUsername = resultRow[DBSetting.Tables.InformationCollection.github_username]
                    )
                } else {
                    null
                }
            }
        }
    }

    override suspend fun createInformation(information: Information): Information {
        return transaction {
            SchemaUtils.create(DBSetting.Tables.InformationCollection)

            val id = DBSetting.Tables.InformationCollection.insert {
                it[first_name] = information.name
                it[last_name] = information.surname
                it[github_username] = information.githubUsername
            } get DBSetting.Tables.InformationCollection.id

            return@transaction information.copy(
                id = id.toString()
            )
        }
    }

    override suspend fun addUserDocumentById(id: String, documentName: String): Boolean {
        return false
    }

    override suspend fun getUserDocumentsById(id: String): List<String>? {
        return null
    }
}