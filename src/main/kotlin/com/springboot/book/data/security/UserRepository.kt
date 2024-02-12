package com.springboot.book.data.security

import org.springframework.data.repository.Repository

interface UserRepository: Repository<UserAccount, Long> {
    fun findByUsername(username: String): UserAccount?
}