package za.co.kaylentravispillay.api.source.service.impl

import za.co.kaylentravispillay.api.data.project.model.response.ResponseProject
import za.co.kaylentravispillay.api.source.service.PPService

class PPServiceLocalImpl : PPService {

    private val mLocalProjectCollection: Map<String, Map<String, List<ResponseProject>>> = mapOf(
        "Test-username" to mapOf(
            "1" to listOf(ResponseProject(name = "Test-Project"))
        )
    )

    override suspend fun getGithubReposByUsername(userName: String, page: String): List<ResponseProject>? {
        return mLocalProjectCollection[userName]?.get(page)
    }
}