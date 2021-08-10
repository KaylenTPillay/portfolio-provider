@file:Suppress("BlockingMethodInNonBlockingContext")

package za.co.kaylentravispillay.api.source.service.delegate

import com.fasterxml.jackson.databind.ObjectMapper
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import za.co.kaylentravispillay.api.data.project.model.response.ResponseProject

class ServiceDelegateGithub {

    private val githubReposURL = "https://api.github.com/users/%{username}/repos"
    private val acceptHeaderValue = "application/vnd.github.v3+json"
    private val userAgentHeaderValue = "ktor client"

    private val mapper = ObjectMapper()
    private val collectionResponseMapperType = mapper.typeFactory.constructCollectionType(
        List::class.java,
        ResponseProject::class.java
    )

    suspend fun getReposByUsername(username: String, page: String): List<ResponseProject>? {
        HttpClient().use { client ->
            val requestURL = githubReposURL.replace("%{username}", username)
            return try {
                val response: HttpResponse = client.get(requestURL) {
                    headers {
                        append(HttpHeaders.Accept, acceptHeaderValue)
                        append(HttpHeaders.UserAgent, userAgentHeaderValue)
                        append("page", page)
                    }
                }

                val contentString = response.receive<String>()
                mapper.readValue(contentString, collectionResponseMapperType)
            } catch (exp: Exception) {
                throw exp
            }
        }
    }
}