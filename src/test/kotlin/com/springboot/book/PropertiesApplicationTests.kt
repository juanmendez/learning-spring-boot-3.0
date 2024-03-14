package com.springboot.book

import org.hamcrest.Matchers.equalTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class PropertiesApplicationTests {
    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun `expect to get same properties found in application-test_properties`() {
        mockMvc.get("/appconfig").andExpect {
            status { isOk() }
        }.andExpect {
            jsonPath("$.header", equalTo("Greetings Test Team!"))
            jsonPath("$.intro", equalTo("If you run into issues while testing, let me know!"))
            jsonPath("$.users[0].username", equalTo("test1"))
            jsonPath("$.users[0].password", equalTo("password"))
            jsonPath("$.users[0].authorities[0]", equalTo(UserRole.ROLE_NOTHING.name))
        }
    }
}
