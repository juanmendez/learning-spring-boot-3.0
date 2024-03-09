package com.springboot.book.data

import java.util.Optional
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class) // Mockito’s JUnit 5 hook to mock out any fields with the @Mock annotation
class VideoServiceTest {
    @Mock
    lateinit var repository: VideoRepository

    lateinit var service: VideoService

    @BeforeEach
    fun onBeforeEach() {
        service = VideoService(repository)
    }

    @Test
    fun getVideosShouldReturnAll() {
        // given
        val video1 = VideoEntity(userName = "alice", name = "Spring Boot 3 Intro", description = "Learn the basics!")
        val video2 = VideoEntity(userName = "alice", name = "Spring Boot 3 Deep Dive", description = "Go deep!")
        Mockito.`when`(repository.findAll()).thenReturn(listOf(video1, video2))

        // when
        val videos = service.getVideos()

        // then
        Assertions.assertThat<List<VideoEntity>>(videos).isEqualTo(listOf(video1, video2))
    }

    @Test
    fun searchShouldReturnASubset() {
        // given
        val video1 = VideoEntity(null, "alice", "Spring Boot 3 Intro", "Learn the basics!")
        Mockito.`when`(repository.findAll(anyString())).thenReturn(listOf(video1))

        // when
        val videos: List<VideoEntity> = service.search(UniversalSearch("Spring Boot 3 Intro"))

        // then
        Assertions.assertThat<List<VideoEntity>>(videos).isEqualTo(listOf(video1))
    }

    @Test
    fun creatingANewVideoShouldReturnTheSameData() {
        // given
        doAnswer {
            VideoEntity(null, "alice", "name", "des")
        }.`when`(repository).saveAndFlush(any())

        // when
        val newVideo = service.create(NewVideo("name", "des"))
        // then
        assertThat(newVideo.name).isEqualTo("name")
        assertThat(newVideo.description).isEqualTo("des")
        assertThat(newVideo.userName).isEqualTo("alice")
    }

    @Test
    fun deletingAVideoShouldWork() {
        // given
        val entity = VideoEntity(1L, "alice", "name", "desc")

        `when`(repository.findById(1L)).thenReturn(Optional.of(entity))
        // when
        service.delete(1L)
        // then
        verify(repository).findById(1L)
        verify(repository).delete(entity)
    }
}