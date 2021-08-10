package za.co.kaylentravispillay.api.data.project.model.response

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class ResponseProject(
    val name: String? = null,
    val description: String? = null,
    val html_url: String? = null,

    val watchers_count: Int? = null,
    val stargazers_count: Int? = null
)