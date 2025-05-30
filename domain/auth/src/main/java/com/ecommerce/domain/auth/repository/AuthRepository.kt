package com.ecommerce.domain.auth.repository

import com.ecommerce.domain.auth.model.User

interface AuthRepository {
    suspend fun signIn(username: String, password: String): User
    suspend fun signUp(email: String, password: String): User
    suspend fun resetPassword(email: String)
    suspend fun signOut()
}
