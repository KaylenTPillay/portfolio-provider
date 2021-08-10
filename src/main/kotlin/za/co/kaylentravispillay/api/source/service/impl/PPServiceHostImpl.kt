package za.co.kaylentravispillay.api.source.service.impl

import za.co.kaylentravispillay.api.data.project.model.response.ResponseProject
import za.co.kaylentravispillay.api.source.service.PPService
import za.co.kaylentravispillay.api.source.service.delegate.ServiceDelegateGithub

class PPServiceHostImpl : PPService {

    private val githubServiceDelegate = ServiceDelegateGithub()

    override suspend fun getGithubReposByUsername(userName: String, page: String): List<ResponseProject>? {
        return githubServiceDelegate.getReposByUsername(userName, page)
    }
}