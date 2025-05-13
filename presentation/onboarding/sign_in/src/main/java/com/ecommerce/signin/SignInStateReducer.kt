package com.ecommerce.signin

import com.ecommerce.presentation.core.base.Reducer

internal class SignInStateReducer : Reducer<SignInState, SignInIntent, SignInEffect> {
    override fun reduce(
        previousState: SignInState,
        intent: SignInIntent
    ): Pair<SignInState, SignInEffect?> = when (intent) {
        is SignInIntent.UpdateEmail -> previousState.copy(email = intent.email) to null
        is SignInIntent.UpdatePassword -> previousState.copy(password = intent.password) to null
        is SignInIntent.UpdateEmailError -> previousState.copy(
            emailError = intent.error,
            isButtonEnabled = previousState.passwordError == null && intent.error == null
        ) to null

        is SignInIntent.UpdatePasswordError -> previousState.copy(
            passwordError = intent.error,
            isButtonEnabled = previousState.emailError == null && intent.error == null
        ) to null

        else -> previousState to null
    }
}
