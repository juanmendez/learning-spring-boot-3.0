package com.springboot.book

import VideoService
import VimeoService
import YoutubeService
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class LearningSpringBootApplication {

    @Bean
    @ConditionalOnProperty(prefix = "my.app", name = ["video"], havingValue = "youtube")
    fun youtubeService(): VideoService = YoutubeService()

    @Bean
    @ConditionalOnProperty(prefix = "my.app", name = ["video"], havingValue = "vimeo")
    fun vimeoService(): VideoService = VimeoService()
}

fun main(args: Array<String>) {
	runApplication<LearningSpringBootApplication>(*args)
}
