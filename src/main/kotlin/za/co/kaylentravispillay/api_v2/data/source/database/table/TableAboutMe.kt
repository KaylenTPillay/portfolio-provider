package za.co.kaylentravispillay.api_v2.data.source.database.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object TableAboutMe : IntIdTable() {
    val type: Column<String> = varchar("type", 255)
    val value: Column<String> = varchar("value", 255)
    val userId: Column<String> = varchar("userId", 255)
    val sortOrder: Column<Int> = integer("sortOrder")
}