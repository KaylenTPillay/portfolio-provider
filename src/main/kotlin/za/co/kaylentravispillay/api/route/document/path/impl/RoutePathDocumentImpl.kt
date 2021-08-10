package za.co.kaylentravispillay.api.route.document.path.impl

import za.co.kaylentravispillay.api.route.document.path.RoutePathDocument

object RoutePathDocumentImpl : RoutePathDocument {
    private const val BASE_PATH = "/documents"

    override val USER_ID_KEY: String = "id"
    override val FILE_NAME_KEY: String = "file_name"

    override val POST_DOCUMENT_BY_ID: String = "$BASE_PATH/{${USER_ID_KEY}}"
    override val GET_USER_DOCUMENTS: String = "$BASE_PATH/{${USER_ID_KEY}}"
    override val GET_USER_DOCUMENT_BY_FILE_NAME: String = "$BASE_PATH/media/{${FILE_NAME_KEY}}"
}