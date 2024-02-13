package com.springboot.book

import com.springboot.book.service.Youtube
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

/**
 *     @Controller: Spring MVC’s annotation to communicate that this class is a web controller.
 *     When the application starts, Spring Boot will automatically detect this class through
 *     component scanning and will create an instance.
 */
@Controller
class HomeController(private val youtube: Youtube) {

    // Spring MVC’s annotation to map HTTP GET / calls to this method.
    @GetMapping("/")
    fun index(model: Model): String {
        model.addAttribute(
            "channelVideos",
            youtube.channelVideos(
                "UCJquYOG5EL82sKTfH9aMA9Q",
                10, Youtube.Sort.VIEW_COUNT
            )
        )

        return "index"
    }
}

