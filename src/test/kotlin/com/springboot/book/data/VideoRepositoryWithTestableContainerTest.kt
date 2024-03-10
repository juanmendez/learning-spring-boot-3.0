package com.springboot.book.data

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.support.TestPropertySourceUtils
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = [VideoRepositoryWithTestableContainerTest.DataSourceInitializer::class])
class VideoRepositoryWithTestableContainerTest {
    companion object {
        @Container
        val database: PostgreSQLContainer<*> = PostgreSQLContainer("postgres:9.6.12").withUsername("postgres")
    }

    class DataSourceInitializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(applicationContext: ConfigurableApplicationContext) {
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                applicationContext,
                "spring.datasource.url=" + database.jdbcUrl,
                "spring.datasource.username=" + database.username,
                "spring.datasource.password=" + database.password,
                "spring.jpa.hibernate.ddl-auto=create-drop"
            )
        }
    }

    @Autowired
    lateinit var videoRepository: VideoRepository

    @BeforeEach
    fun setUp() {
        videoRepository.saveAll(
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
        val videos = videoRepository.findAll()
        Assertions.assertThat(videos).hasSize(3)
    }

    @Test
    fun findByName() {
        val videos = videoRepository.findByNameContainsIgnoreCase("SPRING BOOT 3")
        assertThat(videos).hasSize(1)
    }

    @Test
    fun findByNameOrDescription() {
        val videos: List<VideoEntity> = videoRepository.findByNameContainsOrDescriptionContainsAllIgnoreCase("CODE", "your code")
        assertThat(videos).hasSize(2)
    }
}