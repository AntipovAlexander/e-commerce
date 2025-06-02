package com.ecommerce.data.auth.api

import com.ecommerce.data.auth.model.UserNetworkModel
import com.ecommerce.data.auth.sources.AuthDataSource
import com.ecommerce.data.core.model.ErrorModel
import com.ecommerce.data.core.network.CustomClientErrorException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.delay
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

    @Suppress("MagicNumber")
    override suspend fun restorePassword(string: String) {
        // There is no real api call, so just simulate request
        delay(2000L)
    }

    @Suppress("MagicNumber")
    override suspend fun signUp(email: String, password: String) {
        // There is no real api call, so just simulate request,
        // also some "if" for testing purposes, do not pay attention
        delay(2000L)
        if (email == "taken@email.com") {
            throw CustomClientErrorException(ErrorModel("This email is already taken"))
        }
    }
}
