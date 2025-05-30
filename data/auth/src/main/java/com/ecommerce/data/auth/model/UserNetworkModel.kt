package com.ecommerce.data.auth.model

import kotlinx.serialization.Serializable

@Serializable
data class UserNetworkModel(
    val id: Long,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: GenderNetworkModel,
    val image: String,
    val accessToken: String,
    val refreshToken: String
)
