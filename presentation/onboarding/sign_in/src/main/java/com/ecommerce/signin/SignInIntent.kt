package com.ecommerce.signin

import androidx.compose.runtime.Immutable
import com.ecommerce.core.ui.base.Reducer

@Immutable
sealed class SignInIntent : Reducer.ViewIntent {
    data class UpdateIsLoading(val isLoading: Boolean) : SignInIntent()
    data class UpdateEmail(val email: String, val error: String?) : SignInIntent()
    data class UpdatePassword(val password: String, val error: String?) : SignInIntent()
}
