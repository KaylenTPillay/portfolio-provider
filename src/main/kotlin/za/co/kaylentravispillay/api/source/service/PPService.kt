package za.co.kaylentravispillay.api.source.service

import za.co.kaylentravispillay.api.data.project.model.response.ResponseProject

interface PPService {
    suspend fun getGithubReposByUsername(userName: String, page: String): List<ResponseProject>?
}