package com.springboot.book.data.video

import jakarta.annotation.PostConstruct
import org.springframework.data.domain.Example
import org.springframework.data.domain.ExampleMatcher
import org.springframework.stereotype.Service

@Service
class VideoService(private val videoRepository: VideoRepository) {

    @PostConstruct // A standard Jakarta EE annotation that signals for this method to be run after the application has started
    fun initDatabase() {
        videoRepository.apply {
            save(
                VideoEntity(
                    "alice",
                    NewVideo(
                        "Need HELP with your SPRING BOOT 3 App?",
                        "SPRING BOOT 3 will only speed things up and make it super SIMPLE to serve templates and raw data."
                    )
                )
            )
            save(
                VideoEntity(
                    "alice",
                    NewVideo(
                        "Don't do THIS to your own CODE!",
                        "As a pro developer, never ever EVER do this to your code. Because you'll ultimately be doing it to YOURSELF!"
                    )
                )
            )
            save(
                VideoEntity(
                    "bob",
                    NewVideo(
                        "SECRETS to fix BROKEN CODE!",
                        "Discover ways to not only debug your code, but to regain your confidence and get back in the game as a software developer."
                    )
                )
            )
        }
    }

    fun getVideos(): List<VideoEntity> = videoRepository.findAll()
    fun create(username: String, newVideo: NewVideo): VideoEntity {
        return videoRepository.saveAndFlush(
            VideoEntity(username, newVideo)
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
        val value = universalSearch.value.trim()
        val probe = VideoEntity(name = value, description = value)

        val example = Example.of(
            probe,
            ExampleMatcher.matchingAny().withIgnoreCase().withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
        )

        return videoRepository.findAll(example)
    }

    fun searchByQuery(universalSearch: UniversalSearch) : List<VideoEntity> {
        return videoRepository.findAll(universalSearch.value)
    }

    fun delete(videoId: Long) {
        videoRepository.findById(videoId)
            .map { videoEntity ->
                videoRepository.delete(videoEntity)
                true
            }.orElseThrow {
                RuntimeException("No video at $videoId")
            }
    }
}