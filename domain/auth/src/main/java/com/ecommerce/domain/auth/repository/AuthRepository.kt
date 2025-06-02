package com.ecommerce.domain.auth.repository

import com.ecommerce.domain.auth.model.User

interface AuthRepository {
    suspend fun signIn(username: String, password: String): User
    suspend fun signUp(email: String, password: String)
    suspend fun restorePassword(email: String)
    suspend fun signOut()
}
