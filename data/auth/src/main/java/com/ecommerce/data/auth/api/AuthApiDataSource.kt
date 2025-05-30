package com.ecommerce.data.auth.api

import com.ecommerce.data.auth.model.UserNetworkModel
import com.ecommerce.data.auth.sources.AuthDataSource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import javax.inject.Inject

internal class AuthApiDataSource @Inject constructor(private val client: HttpClient) : AuthDataSource {
    override suspend fun signIn(username: String, password: String) = client.post("auth/login") {
        setBody(
            mapOf(
                "username" to username,
                "password" to password
            )
        )
    }.body<UserNetworkModel>()
}
