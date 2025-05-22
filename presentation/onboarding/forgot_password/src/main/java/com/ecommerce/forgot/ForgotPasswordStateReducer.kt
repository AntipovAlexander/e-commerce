package com.ecommerce.forgot

import com.ecommerce.presentation.core.base.Reducer

internal class ForgotPasswordStateReducer : Reducer<ForgotPasswordState, ForgotPasswordIntent, ForgotPasswordEffect> {
    override fun reduce(
        previousState: ForgotPasswordState,
        intent: ForgotPasswordIntent
    ): Pair<ForgotPasswordState, ForgotPasswordEffect?> = when (intent) {
        is ForgotPasswordIntent.UpdateEmail -> previousState.copy(email = intent.email) to null
        is ForgotPasswordIntent.UpdateEmailError -> previousState.copy(
            emailError = intent.error,
            isButtonEnabled = intent.error == null
        ) to null
        is ForgotPasswordIntent.UpdateIsLoading -> previousState.copy(isLoading = intent.isLoading) to null
    }
}
