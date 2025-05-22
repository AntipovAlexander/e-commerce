package com.ecommerce.forgot

import androidx.compose.runtime.Immutable
import com.ecommerce.presentation.core.base.Reducer

@Immutable
data class ForgotPasswordState(
    val isLoading: Boolean,
    val isButtonEnabled: Boolean,
    val email: String,
    val emailError: String?
) : Reducer.ViewState {
    companion object {
        fun default() = ForgotPasswordState(
            isLoading = false,
            isButtonEnabled = false,
            email = "",
            emailError = null
        )
    }
}
