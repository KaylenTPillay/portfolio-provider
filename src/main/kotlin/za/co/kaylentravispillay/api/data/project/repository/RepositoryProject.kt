package za.co.kaylentravispillay.api.data.project.repository

import za.co.kaylentravispillay.api.data.project.model.Project

interface RepositoryProject {
    suspend fun getProjectsByUsernameForPage(id: String, page: String): List<Project>?
}