package com.springboot.book.data.security

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.persistence.ElementCollection
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails

@Entity
data class UserAccount(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long? = null,
    private val username: String = "",
    private val password: String = "",
    @Transient
    val roles: List<String> = listOf(),
) {
    companion object {
        const val ROLE_USER = "ROLE_USER"
        const val ROLE_ADMIN = "ROLE_ADMIN"
    }

    /**
     * Because the authorities are a collection,
     * JPA 2 offers a simple way to handle this using their @ElementCollection annotation.
     * All these authority values will be stored in a separate table.
     */
    @ElementCollection(fetch = FetchType.EAGER, targetClass = GrantedAuthority::class)
    val authorities: List<GrantedAuthority> = roles
        .map { role: String ->
            SimpleGrantedAuthority(role)
        }.toList()

    fun getUserDetails(): UserDetails {
        return User.withDefaultPasswordEncoder()
            .username(username)
            .password(password)
            .authorities(authorities)
            .build()
    }
}


