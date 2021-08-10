package za.co.kaylentravispillay.api_v2.data.repository

import za.co.kaylentravispillay.api_v2.data.model.DataModelAboutSection

interface RepositoryAbout {
    fun getSections(userId: String): List<DataModelAboutSection>

    fun addSection(userId: String, model: DataModelAboutSection)

    fun updateSection(userId: String, model: DataModelAboutSection)

    fun removeSection(userId: String, model: DataModelAboutSection)
}