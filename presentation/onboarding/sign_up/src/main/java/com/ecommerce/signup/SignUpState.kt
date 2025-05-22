package com.ecommerce.signup

import androidx.compose.runtime.Immutable
import com.ecommerce.presentation.core.base.Reducer

@Immutable
data class SignUpState(
    val isLoading: Boolean,
    val isButtonEnabled: Boolean,
    val email: String,
    val emailError: String?,
    val password: String,
    val passwordError: String?,
    val repeatedPassword: String,
    val repeatedPasswordError: String?
) : Reducer.ViewState {
    companion object {
        fun default() = SignUpState(
            isLoading = false,
            isButtonEnabled = false,
            email = "",
            emailError = null,
            password = "",
            passwordError = null,
            repeatedPassword = "",
            repeatedPasswordError = null
        )
    }
}
