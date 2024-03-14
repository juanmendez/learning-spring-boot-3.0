package com.springboot.book

import java.io.Serializable
import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("app.config")
data class AppConfig(
    val header: String = "",
    val intro: String = "",
    val users: List<UserAccount> = listOf(),
): Serializable
