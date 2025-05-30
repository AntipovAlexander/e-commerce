package com.ecommerce.data.auth.sources

import com.ecommerce.data.auth.model.UserNetworkModel

internal interface AuthDataSource {
    suspend fun signIn(username: String, password: String): UserNetworkModel
}
