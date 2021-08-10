@file:Suppress("PropertyName")

package za.co.kaylentravispillay.api.route.document.path

interface RoutePathDocument {
    val USER_ID_KEY: String
    val FILE_NAME_KEY: String
    val POST_DOCUMENT_BY_ID: String
    val GET_USER_DOCUMENTS: String
    val GET_USER_DOCUMENT_BY_FILE_NAME: String
}