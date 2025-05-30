package com.ecommerce.domain.auth.model

data class User(
    val id: Long,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val gender: Gender,
    val image: String
)
