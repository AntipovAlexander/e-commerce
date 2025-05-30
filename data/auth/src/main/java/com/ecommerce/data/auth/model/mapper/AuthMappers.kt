package com.ecommerce.data.auth.model.mapper

import com.ecommerce.data.auth.model.GenderNetworkModel
import com.ecommerce.data.auth.model.UserNetworkModel
import com.ecommerce.domain.auth.model.Gender
import com.ecommerce.domain.auth.model.User

fun UserNetworkModel.toDomainModel() = User(
    id = id,
    username = username,
    email = email,
    firstName = firstName,
    lastName = lastName,
    gender = gender.toDomainModel(),
    image = image
)

fun GenderNetworkModel.toDomainModel() = when (this) {
    GenderNetworkModel.MALE -> Gender.MALE
    GenderNetworkModel.FEMALE -> Gender.FEMALE
    GenderNetworkModel.OTHER -> Gender.OTHER
}
