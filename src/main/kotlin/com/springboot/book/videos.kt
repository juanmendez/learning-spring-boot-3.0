package com.springboot.book

import org.springframework.stereotype.Service

data class Video(val name: String)

@Service
class VideoService {
    private var videos = mutableListOf(
        Video("Need HELP with your SPRING BOOT 3 App?"),
        Video("Don't do THIS to your own CODE!"),
        Video("SECRETS to fix BROKEN CODE!")
    )

    fun getVideos(): List<Video> {
        return videos
    }

    fun create(newVideo: Video): Video? {
        return if (newVideo.name.isNotBlank() && videos.none { it.name.equals(newVideo.name, true) }) {
            videos.add(newVideo)
            newVideo
        } else {
            null
        }
    }
}