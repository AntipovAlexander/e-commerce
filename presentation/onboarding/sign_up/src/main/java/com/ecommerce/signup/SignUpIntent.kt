package com.ecommerce.signup

import androidx.compose.runtime.Immutable
import com.ecommerce.presentation.core.base.Reducer

@Immutable
sealed class SignUpIntent : Reducer.ViewIntent {
    data class UpdateIsLoading(val isLoading: Boolean) : SignUpIntent()
    data class UpdateEmail(val email: String) : SignUpIntent()
    data class UpdateEmailError(val error: String?) : SignUpIntent()
    data class UpdatePassword(val password: String) : SignUpIntent()
    data class UpdatePasswordError(val error: String?) : SignUpIntent()
    data class UpdateRepeatedPassword(val password: String) : SignUpIntent()
    data class UpdateRepeatedPasswordError(val error: String?) : SignUpIntent()
}
