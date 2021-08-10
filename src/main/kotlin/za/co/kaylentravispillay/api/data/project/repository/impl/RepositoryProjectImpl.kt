package za.co.kaylentravispillay.api.data.project.repository.impl

import za.co.kaylentravispillay.api.data.project.model.Project
import za.co.kaylentravispillay.api.data.project.repository.RepositoryProject
import za.co.kaylentravispillay.api.source.service.PPService

class RepositoryProjectImpl(
    private val service: PPService
) : RepositoryProject {

    override suspend fun getProjectsByUsernameForPage(id: String, page: String): List<Project>? {
        return service.getGithubReposByUsername(id, page)?.map { responseProject ->
            Project(
                name = responseProject.name ?: String(),
                description = responseProject.description ?: String(),
                url = responseProject.html_url ?: String(),
                watcher = responseProject.watchers_count ?: 0,
                stars = responseProject.stargazers_count ?: 0
            )
        }
    }
}