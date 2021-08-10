package za.co.kaylentravispillay.information

import io.ktor.http.*
import io.ktor.server.testing.*
import za.co.kaylentravispillay.module
import kotlin.test.Test
import kotlin.test.assertEquals

internal class InformationRouteTest {

    @Test
    fun `given get information when id is not known then respond with error response`() {
        val mockId = "-1"

        val expectedResponse = """
            {"code":"NO_INFORMATION_FOUND","message":"No user found for provided id"}
        """.trimIndent()

        withTestApplication(
            { module(true) }
        ) {
            handleRequest(HttpMethod.Get, "/information/$mockId").apply {
                assertEquals(expectedResponse, response.content)
                assertEquals(HttpStatusCode.BadRequest, response.status())
            }
        }
    }

    @Test
    fun `given get information when id is known then respond with successful response`() {
        val mockId = "0"

        val expectedResponse = """
            {"user_information":{"id":"0","name":"Test-name","surname":"Test-surname","githubUsername":"Test-username"}}
        """.trimIndent()

        withTestApplication(
            { module(true) }
        ) {
            handleRequest(HttpMethod.Get, "/information/$mockId").apply {
                assertEquals(expectedResponse, response.content)
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun `given post information when body is not formatted correctly then response with error response`() {
        val mockPostBody = """
            {"invalid": "somebody", "data": "something"}
        """.trimIndent()

        val expectedResponse = """
            {"code":"UNABLE_TO_CREATE","message":"We are unable to create new information. Please try again later"}
        """.trimIndent()

        withTestApplication(
            { module(true) }
        ) {
            with(
                handleRequest(HttpMethod.Post, "/information") {
                    setBody(mockPostBody)
                }
            ) {
                assertEquals(expectedResponse, response.content)
                assertEquals(HttpStatusCode.BadRequest, response.status())
            }
        }
    }

    @Test
    fun `given post information when body is formatted correctly then response with successful response`() {
        val mockPostBody = """
            {"name": "somebody", "surname": "something","githubUsername":"somename"}
        """.trimIndent()

        val expectedResponse = """
            {"user_information":{"id":"1","name":"somebody","surname":"something","githubUsername":"somename"}}
        """.trimIndent()

        withTestApplication(
            { module(true) }
        ) {
            with(
                handleRequest(HttpMethod.Post, "/information") {
                    addHeader(HttpHeaders.ContentType, ContentType.Application.Json.toString())
                    setBody(mockPostBody)
                }
            ) {
                assertEquals(expectedResponse, response.content)
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }
}