package com.springboot.book

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
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
    fun index(model: Model): String {
        model.addAttribute("videos", videoService.getVideos())
        return "index"
    }

    @GetMapping("/react")
    fun react(): String {
        return "react"
    }

    @PostMapping("/new-video") // Spring MVC’s annotation to capture POST /new-video calls and route them to this method.
    fun newVideo(
        @ModelAttribute newVideo: Video // Spring MVC’s annotation to parse an incoming HTML form and unpack it into a Video object.
    ): String? {
        videoService.create(newVideo)

        return "redirect:/" // Spring MVC directive that sends the browser an HTTP 302 Found to URL /.
    }
}

