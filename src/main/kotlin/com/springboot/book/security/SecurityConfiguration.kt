package com.springboot.book.security

import com.springboot.book.data.security.UserAccount
import com.springboot.book.data.security.UserManagementRepository
import com.springboot.book.data.security.UserRepository
import java.util.function.Supplier
import java.util.stream.Stream
import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authorization.AuthorityAuthorizationManager
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.intercept.RequestAuthorizationContext

@Configuration // Spring’s annotation to signal that this class is a source of bean definitions rather than actual application code.
@EnableMethodSecurity // Spring Security’s annotation to activate method-based security
class SecurityConfiguration {
    /**
     * Start up the application with the given users
     */
    @Bean
    fun initUsers(userManagementRepository: UserManagementRepository): CommandLineRunner {
        return CommandLineRunner {
            userManagementRepository.apply {
                save(
                    UserAccount(username = "alice", password = "password", roles = listOf(UserAccount.ROLE_USER))
                )

                save(
                    UserAccount(username = "bob", password = "password", roles = listOf(UserAccount.ROLE_USER))
                )

                save(
                    UserAccount(username = "admin", password = "password", roles = listOf(UserAccount.ROLE_ADMIN))
                )
            }
        }
    }

    /**
     * UserDetailsService, Spring Security’s interface for defining a source of users.
     */
    @Bean
    fun userService(userRepository: UserRepository): UserDetailsService {
        return UserDetailsService { username ->
            userRepository.findByUsername(username)?.getUserDetails()
        }
    }

    @Bean
    @Throws(java.lang.Exception::class)
    fun configureSecurity(http: HttpSecurity): SecurityFilterChain? {
        return http.authorizeHttpRequests { authz ->
            authz.requestMatchers("/resources/**", "/about", "/login").permitAll()


            authz.requestMatchers("/", "/universal-search", "/delete/videos/**").authenticated()
            authz.requestMatchers(HttpMethod.GET, "/api/**").authenticated()
            authz.requestMatchers(HttpMethod.POST, "/delete/videos/**", "/new-video").authenticated()

            authz.requestMatchers(HttpMethod.GET, "/admin/**").hasRole("ADMIN")

            authz.requestMatchers("/db/**")
                .access { authentication: Supplier<Authentication?>?, requestAuthorizationContext: RequestAuthorizationContext ->
                    val anyMissing = Stream.of("ADMIN", "DBA")
                        .map { role: String? ->
                            AuthorityAuthorizationManager.hasRole<Any>(role).check(authentication, requestAuthorizationContext)?.isGranted == true
                        }
                        .filter { granted -> !granted }
                        .findAny()
                        .orElse(false)
                    AuthorizationDecision(!anyMissing)
                }
                .anyRequest()
                .denyAll()
        }.formLogin {}
            .httpBasic {}
            .build()
    }
}