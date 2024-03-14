package com.springboot.book

import java.io.Serializable

data class UserAccount(
    val username: String = "",
    val password: String = "",
    val authorities: List<UserRole> = listOf(UserRole.ROLE_NOTHING),
): Serializable

enum class UserRole(val value: String) : Serializable {
    ROLE_NOTHING("NOTHING"),
    ROLE_USER("USER"),
    ROLE_ADMIN("ADMIN")
}