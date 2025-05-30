package com.ecommerce.data.auth.model

import kotlinx.serialization.SerialName

enum class GenderNetworkModel {
    @SerialName("male")
    MALE,

    @SerialName("female")
    FEMALE,

    @SerialName("other")
    OTHER
}
