package za.co.kaylentravispillay.api_v2.data.model.mapping

import za.co.kaylentravispillay.api_v2.data.model.DataModelAboutSection
import za.co.kaylentravispillay.api_v2.data.model.DataModelUser
import za.co.kaylentravispillay.api_v2.data.source.database.table.model.EntityAboutMeSection
import za.co.kaylentravispillay.api_v2.data.source.database.table.model.EntityUser

fun EntityUser.mapToDataModel() = DataModelUser(
    id = id,
    name = name,
    surname = surname,
    image = image
)

fun EntityAboutMeSection.mapToDataModel() = DataModelAboutSection(
    id = id,
    type = type,
    value = value,
    sortOrder = sortOrder
)