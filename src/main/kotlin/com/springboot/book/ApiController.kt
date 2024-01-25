package com.springboot.book

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController // annotation marks this as a Spring MVC controller that returns JSON
class ApiController(private val videoService: VideoService) {
    @GetMapping("/api/videos")
    fun allVideos() = videoService.getVideos()

    @PostMapping("/api/videos") // Spring MVC’s annotation to capture POST /new-video calls and route them to this method.
    fun newVideo(
        @RequestBody newVideo: Video,
        // Spring MVC’s annotation to signal that the incoming HTTP request body should be
        // deserialized via Jackson into the newVideo argument as a Video record
    ): Video? {
        return videoService.create(newVideo)
    }
}