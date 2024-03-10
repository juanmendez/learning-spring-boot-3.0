package com.springboot.book;

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain

@Configuration
@EnableMethodSecurity
class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun configureSecurity(http: HttpSecurity): SecurityFilterChain {
        return http.authorizeHttpRequests { registry ->
            registry.requestMatchers("/login").permitAll()
                .requestMatchers("/", "/search").authenticated()
                .requestMatchers(HttpMethod.GET, "/api/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/delete/**", "/new-video").authenticated()
                .anyRequest().denyAll()
        }.formLogin {

        }.httpBasic {

        }.build()
    }
}