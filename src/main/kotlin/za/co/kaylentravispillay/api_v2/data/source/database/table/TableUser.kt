package za.co.kaylentravispillay.api_v2.data.source.database.table

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.Column

object TableUser : IntIdTable() {
    val name: Column<String> = varchar("name", 255)
    val surname: Column<String> = varchar("surname", 255)
    val image: Column<String> = varchar("image", 500)
}