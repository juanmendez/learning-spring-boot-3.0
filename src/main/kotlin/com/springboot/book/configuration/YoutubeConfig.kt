package com.springboot.book.configuration

import com.springboot.book.service.Youtube
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.support.WebClientAdapter
import org.springframework.web.service.invoker.HttpServiceProxyFactory

@Configuration
@Suppress("unused")
class YoutubeConfig {
    companion object {
        const val GOOGLE = "google"
        const val YOUTUBE_V3_API = "https://www.googleapis.com/youtube/v3"
    }

    @Bean
    fun webClient(clientManager: OAuth2AuthorizedClientManager): WebClient {
        val oauth2 = ServletOAuth2AuthorizedClientExchangeFilterFunction(clientManager)
        oauth2.setDefaultClientRegistrationId(GOOGLE)
        return WebClient.builder()
            .baseUrl(YOUTUBE_V3_API)
            .apply(oauth2.oauth2Configuration())
            .build()
    }

    @Bean
    fun proxyFactory(webClient: WebClient): HttpServiceProxyFactory {
        return HttpServiceProxyFactory.builder().exchangeAdapter(
            WebClientAdapter.create(webClient)
        ).build()
    }

    @Bean
    fun client(factory: HttpServiceProxyFactory) = factory.createClient(Youtube::class.java)
}