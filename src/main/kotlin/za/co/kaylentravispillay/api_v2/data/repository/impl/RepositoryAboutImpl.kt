package za.co.kaylentravispillay.api_v2.data.repository.impl

import za.co.kaylentravispillay.api_v2.data.model.DataModelAboutSection
import za.co.kaylentravispillay.api_v2.data.model.mapping.mapToDataModel
import za.co.kaylentravispillay.api_v2.data.repository.RepositoryAbout
import za.co.kaylentravispillay.api_v2.data.source.database.Database
import za.co.kaylentravispillay.api_v2.data.source.database.table.transaction.impl.TableTransactionAboutMe
import za.co.kaylentravispillay.api_v2.data.source.database.table.transaction.type.TableTransactionType

class RepositoryAboutImpl(database: Database) : RepositoryAbout {

    private val mAboutTableTransaction =
        database.getTableTransaction(TableTransactionType.ABOUT_ME) as TableTransactionAboutMe

    override fun getSections(userId: String): List<DataModelAboutSection> {
        return mAboutTableTransaction.getSections(userId).map { entity ->
            entity.mapToDataModel()
        }
    }

    override fun addSection(userId: String, model: DataModelAboutSection) {
        mAboutTableTransaction.addSection(userId, model.value, model.type, model.sortOrder)
    }

    override fun updateSection(userId: String, model: DataModelAboutSection) {
        model.id.toIntOrNull()?.let { idInt ->
            mAboutTableTransaction.updateSection(
                userId = userId,
                sectionId = idInt,
                value = model.value,
                type = model.type,
                sortOrder = model.sortOrder
            )
        }
    }

    override fun removeSection(userId: String, model: DataModelAboutSection) {
        model.id.toIntOrNull()?.let { idInt ->
            mAboutTableTransaction.removeSection(userId, idInt)
        }
    }
}