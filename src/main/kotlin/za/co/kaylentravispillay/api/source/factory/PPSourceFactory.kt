package za.co.kaylentravispillay.api.source.factory

interface PPSourceFactory<out SOURCE> {
    fun create(): SOURCE
}