package com.springboot.book.data

@Suppress("unused")
data class SearchSnippet(
    val publishedAt: String?,
    val channelId: String?,
    val title: String?,
    val description: String?,
    val thumbnails: Map<String, SearchThumbnail>?,
    val channelTitle: String?,
) {
    val shortDescription: String?
        get() {
            val length = description?.length?.coerceAtMost(100) ?: 0
            return description?.substring(0, length)
        }

    val thumbnail = thumbnails?.entries?.firstOrNull { entry ->
        entry.key == "default"
    }?.value
}