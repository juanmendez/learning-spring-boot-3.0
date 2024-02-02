package com.springboot.book.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

// Spring’s annotation to signal that this class is a source of bean definitions rather than actual application code.
@Configuration
class SecurityConfiguration {
    /**
     * UserDetailsService, Spring Security’s interface for defining a source of users.
     */
    @Bean
    fun userDetailsService(): UserDetailsService = InMemoryUserDetailsManager().apply {
        // in this code so far, we used withDefaultPasswordEncoder() to store passwords
        createUser(
            User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build()
        )

        createUser(
            User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password")
                .roles("USER")
                .build()
        )
    }
}