package com.ecommerce.signin

import androidx.compose.runtime.Immutable
import com.ecommerce.presentation.core.base.Reducer

@Immutable
data class SignInState(
    val isLoading: Boolean,
    val isButtonEnabled: Boolean,
    val email: String,
    val emailError: String?,
    val password: String,
    val passwordError: String?
) : Reducer.ViewState {
    companion object {
        fun default() = SignInState(
            isLoading = false,
            isButtonEnabled = true,
            email = "emily.johnson@x.dummyjson.com",
            emailError = null,
            password = "emilyspass",
            passwordError = null,
        )
    }
}
