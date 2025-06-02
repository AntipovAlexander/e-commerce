package com.ecommerce.data.auth.repository

import com.ecommerce.data.auth.model.mapper.toDomainModel
import com.ecommerce.data.auth.sources.AuthDataSource
import com.ecommerce.domain.auth.model.User
import com.ecommerce.domain.auth.repository.AuthRepository
import javax.inject.Inject

internal class AuthRepositoryImpl @Inject constructor(
    private val authDataSource: AuthDataSource
) : AuthRepository {

    override suspend fun signIn(username: String, password: String): User =
        authDataSource.signIn(username, password).toDomainModel()

    override suspend fun signUp(email: String, password: String) =
        authDataSource.signUp(email, password)

    override suspend fun restorePassword(email: String) =
        authDataSource.restorePassword(email)

    override suspend fun signOut() {
        TODO()
    }
}
