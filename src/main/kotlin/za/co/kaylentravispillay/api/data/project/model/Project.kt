package za.co.kaylentravispillay.api.data.project.model

data class Project(
    val name: String = String(),
    val description: String = String(),
    val url: String = String(),

    val watcher: Int = 0,
    val stars: Int = 0
)