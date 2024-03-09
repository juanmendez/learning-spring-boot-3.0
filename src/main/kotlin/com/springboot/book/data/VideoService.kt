package com.springboot.book.data

import jakarta.annotation.PostConstruct
import org.springframework.stereotype.Service

@Service
class VideoService(private val videoRepository: VideoRepository) {

    @PostConstruct
    fun initDatabase() {
        videoRepository.apply {
            save(
                VideoEntity(
                    NewVideo(
                        "Need HELP with your SPRING BOOT 3 App?",
                        "SPRING BOOT 3 will only speed things up and make it super SIMPLE to serve templates and raw data."
                    )
                )
            )
            save(
                VideoEntity(
                    NewVideo(
                        "Don't do THIS to your own CODE!",
                        "As a pro developer, never ever EVER do this to your code. Because you'll ultimately be doing it to YOURSELF!"
                    )
                )
            )
            save(
                VideoEntity(
                    NewVideo(
                        "SECRETS to fix BROKEN CODE!",
                        "Discover ways to not only debug your code, but to regain your confidence and get back in the game as a software developer."
                    )
                )
            )
        }
    }

    fun getVideos(): List<VideoEntity> = videoRepository.findAll()
    fun create(newVideo: NewVideo): VideoEntity {
        return videoRepository.saveAndFlush(
            VideoEntity(newVideo)
        )
    }

    fun search(videoSearch: VideoSearch): List<VideoEntity> {
        val name = videoSearch.name
        val description = videoSearch.description

        return if (name.isNotBlank() && description.isNotBlank()) {
            videoRepository.findByNameContainsOrDescriptionContainsAllIgnoreCase(name, description)
        } else if (name.isNotBlank()) {
            videoRepository.findByNameContainsIgnoreCase(name)
        } else if (description.isNotBlank()) {
            videoRepository.findByDescriptionContainsIgnoreCase(description)
        } else {
            emptyList()
        }
    }

    fun search(universalSearch: UniversalSearch): List<VideoEntity> {
        return videoRepository.findAll(universalSearch.value.trim())
    }

    fun searchByQuery(universalSearch: UniversalSearch): List<VideoEntity> {
        return videoRepository.findAll(universalSearch.value)
    }

    @Throws(RuntimeException::class)
    fun delete(id: Long): Boolean {
        return videoRepository.findById(id)
            .map { videoEntity ->
                videoRepository.delete(videoEntity)
                true
            }.orElseThrow { RuntimeException("No video at $id") }
    }
}