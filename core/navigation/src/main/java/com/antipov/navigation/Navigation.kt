package com.antipov.navigation

import androidx.navigation.NavOptionsBuilder
import kotlinx.serialization.Serializable

interface Navigation

@Serializable
data object PreviousScreen : Navigation

typealias OnNavigateTo = (Navigation, NavOptionsBuilder.() -> Unit) -> Unit