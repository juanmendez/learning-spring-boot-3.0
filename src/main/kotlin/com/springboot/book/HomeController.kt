package com.springboot.book

import com.springboot.book.data.video.NewVideo
import com.springboot.book.data.video.UniversalSearch
import com.springboot.book.data.video.VideoEntity
import com.springboot.book.data.video.VideoSearch
import com.springboot.book.data.video.VideoService
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

/**
 *     @Controller: Spring MVC’s annotation to communicate that this class is a web controller.
 *     When the application starts, Spring Boot will automatically detect this class through
 *     component scanning and will create an instance.
 */
@Controller
class HomeController(private val videoService: VideoService) {

    // Spring MVC’s annotation to map HTTP GET / calls to this method.
    @GetMapping("/")
    fun index(authentication: Authentication, model: Model): String {
        model.addAttribute("authentication", authentication)
        model.addAttribute("videos", videoService.getVideos())
        return "index"
    }

    @PostMapping("/new-video") // Spring MVC’s annotation to capture POST /new-video calls and route them to this method.
    fun newVideo(
        @ModelAttribute newVideo: NewVideo, // Spring MVC’s annotation to parse an incoming HTML form and unpack it into a Video object.
        authentication: Authentication,
    ): String? {
        videoService.create(authentication.name, newVideo)

        return "redirect:/" // Spring MVC directive that sends the browser an HTTP 302 Found to URL /.
    }

    @PostMapping("/multi-field-search")
    fun multiFieldSearch(
        // The @ModelAttribute annotation is Spring MVC’s signal to deserialize the incoming form
        @ModelAttribute search: VideoSearch,
        model: Model,
        authentication: Authentication,
    ): String? {
        val searchResults: List<VideoEntity> = videoService.search(search)
        model.addAttribute("videos", searchResults)
        model.addAttribute("authentication", authentication)
        return "index"
    }

    @PostMapping("/universal-search")
    fun universalSearch(
        @ModelAttribute search: UniversalSearch,
        model: Model,
        authentication: Authentication,
    ): String {
        val searchResults = videoService.search(search)

        model.addAttribute("search", search)
        model.addAttribute("videos", searchResults)
        model.addAttribute("authentication", authentication)
        return "index"
    }

    @PostMapping("/universal-search-query")
    fun universalSearchQuery(
        @ModelAttribute search: UniversalSearch,
        model: Model,
        authentication: Authentication,
    ): String {
        val searchResults = videoService.searchByQuery(search)
        model.addAttribute("videos", searchResults)
        model.addAttribute("authentication", authentication)
        return "index"
    }

    @PostMapping("/delete/videos/{videoId}")
    fun deleteVideo(@PathVariable videoId: Long): String? {
        videoService.delete(videoId)
        return "redirect:/"
    }
}

