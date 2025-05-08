package com.ecommerce.signin

import androidx.compose.runtime.Immutable
import com.ecommerce.core.ui.base.Reducer

@Immutable
sealed class SignInIntents : Reducer.ViewIntent {
    data class UpdateIsLoading(val isLoading: Boolean) : SignInIntents()
}
