package com.springboot.book

import com.springboot.book.data.NewVideo
import com.springboot.book.data.VideoService
import org.assertj.core.api.Assertions
import org.hamcrest.Matchers
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(controllers = [HomeController::class])
class HomeControllerTest {
    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var videoService: VideoService

    @Test
    @WithMockUser
    fun `index page has serveral html forms`() {
        val html = mvc.perform(
            MockMvcRequestBuilders.get("/")
        )
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andExpect(
                MockMvcResultMatchers.content().string(
                    Matchers.containsString("Greetings Learning Spring Boot 3.0 fans!")
                )
            )
            .andReturn()
            .response.contentAsString

        Assertions.assertThat(html).contains(
            "<form action=\"/multi-field-search\"",
            "<form action=\"/new-video\""
        )
    }

    @Test
    @WithMockUser // Gets us past Spring Security’s checks by simulated authentication.
    fun postNewVideoShouldWork() {
        mvc.perform(
            MockMvcRequestBuilders.post("/new-video")
                .param("name", "new video")
                .param("description", "new desc")
                .with(SecurityMockMvcRequestPostProcessors.csrf())
        )
            .andExpect(MockMvcResultMatchers.redirectedUrl("/"))

        Mockito.verify(videoService).create(
            NewVideo(
                "new video",
                "new desc"
            )
        )
    }
}