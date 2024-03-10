package com.springboot.book.data

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

// This is Spring Boot’s test annotation and indicates that we want it to perform all of its automated scanning of entity class definitions and Spring Data JPA repositories
@DataJpaTest
class VideoRepositoryHsqlTest {
    @Autowired
    lateinit var repository: VideoRepository

    @BeforeEach
    fun doBeforeEach() {
        repository.saveAll(
            listOf(
                VideoEntity(
                    null,
                    "alice",
                    "Need HELP with your SPRING BOOT 3 App?",
                    "SPRING BOOT 3 will only speed things up."
                ),
                VideoEntity(
                    null,
                    "alice",
                    "Don't do THIS to your own CODE!",
                    "As a pro developer, never ever EVER do this to your code."
                ),
                VideoEntity(
                    null,
                    "bob",
                    "SECRETS to fix BROKEN CODE!",
                    "Discover ways to not only debug your code"
                )
            )
        )
    }

    @Test
    fun findAllShouldProduceAllVideos() {
        val videos = repository.findAll()
        assertThat(videos).hasSize(3)
    }

    @Test
    fun findByNameShouldRetrieveOneEntry() {
        val videos = repository.findByNameContainsIgnoreCase("SpRinG bOOt 3")
        assertThat(videos).hasSize(1)
        assertThat(videos.map { it.name }).containsExactlyInAnyOrder("Need HELP with your SPRING BOOT 3 App?")
    }

    @Test
    fun findByNameOrDescriptionShouldFindTwo() {
        val videos = repository.findByNameContainsOrDescriptionContainsAllIgnoreCase("CoDe", "YOUR CODE")
        assertThat(videos).hasSize(2)
        assertThat(videos.map { it.description })
            .containsExactlyInAnyOrder(
                "As a pro developer, never ever EVER do this to your code.", 
                "Discover ways to not only debug your code"
            )
    }
}