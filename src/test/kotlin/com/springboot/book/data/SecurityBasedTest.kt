package com.springboot.book.data

import com.springboot.book.HomeController
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [HomeController::class])
class SecurityBasedTest {
    @Autowired
    lateinit var mvc: MockMvc

    @MockBean
    lateinit var videoService: VideoService

    @Test
    @Throws(Exception::class)
        /**
         * It does NOT have one of those @WithMockUser annotations.
         * This means no authentication credentials are stored in the servlet context, thus simulating an unauthorized user.
         */
    fun unauthUserShouldNotAccessHomePage() {
        mvc.perform(get("/")).andExpect(status().isUnauthorized())
    }

    @Test
    @WithMockUser(username = "alice", roles = ["USER"])
    @Throws(Exception::class)
    fun authUserShouldAccessHomePage() {
        mvc.perform(get("/")).andExpect(status().isOk())
    }

    @Test
    @WithMockUser(username = "alice", roles = ["ADMIN"])
    @Throws(Exception::class)
    fun adminShouldAccessHomePage() {
        mvc.perform(get("/")).andExpect(status().isOk())
    }

    @Test
    @Throws(Exception::class)
    fun newVideoFromUnauthUserShouldFail() {
        mvc.perform(
            MockMvcRequestBuilders.post("/new-video")
                .param("name", "new video")
                .param("description", "new desc")
                .with(csrf())
        ).andExpect(status().isUnauthorized())
    }

    @Test
    @WithMockUser(username = "alice", roles = ["USER"])
    @Throws(Exception::class)
    fun newVideoFromUserShouldWork() {
        mvc.perform(
            MockMvcRequestBuilders.post("/new-video")
                .param("name", "new video")
                .param("description", "new desc")
                .with(csrf())
        )
            .andExpect(status().is3xxRedirection()) // Verifies that we get something in the 300 series of HTTP response signals.
            .andExpect(redirectedUrl("/")) // Lets us verify that the redirected path is /.
    }
}