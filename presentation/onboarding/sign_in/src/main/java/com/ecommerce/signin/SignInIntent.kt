package com.ecommerce.signin

import androidx.compose.runtime.Immutable
import com.ecommerce.presentation.core.base.Reducer

@Immutable
sealed class SignInIntent : Reducer.ViewIntent {
    data class UpdateIsLoading(val isLoading: Boolean) : SignInIntent()
    data class UpdateEmail(val email: String) : SignInIntent()
    data class UpdateEmailError(val error: String?) : SignInIntent()
    data class UpdatePassword(val password: String) : SignInIntent()
    data class UpdatePasswordError(val error: String?) : SignInIntent()
}
