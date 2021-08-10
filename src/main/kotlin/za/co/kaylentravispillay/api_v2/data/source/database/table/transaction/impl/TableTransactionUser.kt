package za.co.kaylentravispillay.api_v2.data.source.database.table.transaction.impl

import org.jetbrains.exposed.sql.deleteWhere
import org.jetbrains.exposed.sql.insertAndGetId
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.statements.UpdateStatement
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import za.co.kaylentravispillay.api_v2.data.source.database.table.TableUser
import za.co.kaylentravispillay.api_v2.data.source.database.table.model.EntityUser
import za.co.kaylentravispillay.api_v2.data.source.database.table.transaction.TableTransaction

class TableTransactionUser : TableTransaction {

    fun createUser(name: String, surname: String, image: String = String()): String {
        return transaction {
            TableUser.insertAndGetId {
                it[this.name] = name
                it[this.surname] = surname
                it[this.image] = image
            }
        }.value.toString()
    }

    fun getUser(id: Int): EntityUser? {
        return transaction {
            TableUser.select(where = { TableUser.id eq id }).map {
                EntityUser(
                    id = it[TableUser.id].value.toString(),
                    name = it[TableUser.name],
                    surname = it[TableUser.surname],
                    image = it[TableUser.image]
                )
            }.singleOrNull()
        }
    }

    fun updateUserName(userId: Int, name: String) {
        updatePropertyForUserId(userId) {
            it[this.name] = name
        }
    }

    fun updateUserSurname(userId: Int, surname: String) {
        updatePropertyForUserId(userId) {
            it[this.surname] = surname
        }
    }

    fun updateUserImage(userId: Int, image: String) {
        updatePropertyForUserId(userId) {
            it[this.image] = image
        }
    }

    fun deleteUser(userId: Int) {
        transaction {
            TableUser.deleteWhere {
                TableUser.id eq userId
            }
        }
    }

    private fun updatePropertyForUserId(userId: Int, onUpdate: TableUser.(UpdateStatement) -> Unit) {
        transaction {
            TableUser.update(
                where = { TableUser.id eq userId },
                body = onUpdate
            )
        }
    }
}