package com.ecommerce.forgot

import androidx.compose.runtime.Immutable
import com.ecommerce.presentation.core.base.Reducer

@Immutable
sealed class ForgotPasswordIntent : Reducer.ViewIntent {
    data class UpdateIsLoading(val isLoading: Boolean) : ForgotPasswordIntent()
    data class UpdateEmail(val email: String) : ForgotPasswordIntent()
    data class UpdateEmailError(val error: String?) : ForgotPasswordIntent()
}
