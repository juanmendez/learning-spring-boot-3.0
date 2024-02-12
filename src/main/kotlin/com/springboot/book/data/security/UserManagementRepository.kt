package com.springboot.book.data.security

import org.springframework.data.jpa.repository.JpaRepository

interface UserManagementRepository : JpaRepository<UserAccount, Long>