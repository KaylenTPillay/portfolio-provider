package za.co.kaylentravispillay.api_v2.data.source.database.table.transaction.impl

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import za.co.kaylentravispillay.api_v2.data.source.database.table.TableAboutMe
import za.co.kaylentravispillay.api_v2.data.source.database.table.model.EntityAboutMeSection
import za.co.kaylentravispillay.api_v2.data.source.database.table.transaction.TableTransaction

class TableTransactionAboutMe : TableTransaction {

    fun addSection(userId: String, value: String, type: String, sortOrder: Int) {
        transaction {
            TableAboutMe.insert {
                it[this.type] = type
                it[this.value] = value
                it[this.userId] = userId
                it[this.sortOrder] = sortOrder
            }
        }
    }

    fun removeSection(userId: String, sectionId: Int) {
        transaction {
            TableAboutMe.deleteWhere {
                (TableAboutMe.id eq sectionId) and (TableAboutMe.userId eq userId)
            }
        }
    }

    fun updateSection(userId: String, sectionId: Int, value: String, type: String, sortOrder: Int) {
        transaction {
            TableAboutMe.update(
                where = {
                    (TableAboutMe.id eq sectionId) and (TableAboutMe.userId eq userId)
                }
            ) {
                it[this.value] = value
                it[this.type] = type
                it[this.sortOrder] = sortOrder
            }
        }
    }

    fun getSections(userId: String): List<EntityAboutMeSection> {
        return transaction {
            TableAboutMe.select(where = { TableAboutMe.userId eq userId })
                .orderBy(TableAboutMe.sortOrder to SortOrder.ASC)
                .map {
                    EntityAboutMeSection(
                        id = it[TableAboutMe.id].value.toString(),
                        type = it[TableAboutMe.type],
                        value = it[TableAboutMe.value],
                        sortOrder = it[TableAboutMe.sortOrder]
                    )
                }
        }
    }
}