package com.springboot.book.service

import com.springboot.book.data.SearchListResponse
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.service.annotation.GetExchange

@Suppress("unused")
interface Youtube {
    @GetExchange("/search?part=snippet&type=video")
    fun channelVideos(
        @RequestParam channelId: String?,
        @RequestParam maxResults: Int?,
        @RequestParam order: Sort?,
    ): SearchListResponse?

    enum class Sort(private val type: String) {
        DATE("date"),
        VIEW_COUNT("viewCount"),
        TITLE("title"),
        RATING("rating")
    }

}