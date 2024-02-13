package com.springboot.book.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientProviderBuilder
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository

@Configuration // Spring’s annotation to signal that this class is a source of bean definitions rather than actual application code.
@EnableMethodSecurity // Spring Security’s annotation to activate method-based security
class SecurityConfiguration {
    @Bean
    fun clientManager(
        clientRegistrationRepository: ClientRegistrationRepository,
        authClientRepository: OAuth2AuthorizedClientRepository,
    ): OAuth2AuthorizedClientManager {

        return DefaultOAuth2AuthorizedClientManager(
            clientRegistrationRepository,
            authClientRepository,
        ).apply {
            setAuthorizedClientProvider(
                OAuth2AuthorizedClientProviderBuilder.builder()
                    .authorizationCode()
                    .refreshToken()
                    .clientCredentials()
                    .password()
                    .build()
            )
        }
    }
}