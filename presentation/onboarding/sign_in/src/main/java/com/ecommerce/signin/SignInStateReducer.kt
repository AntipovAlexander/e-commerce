package com.ecommerce.signin

import com.ecommerce.presentation.core.base.Reducer

internal class SignInStateReducer : Reducer<SignInState, SignInIntent, SignInEffect> {
    override fun reduce(previousState: SignInState, intent: SignInIntent): Pair<SignInState, SignInEffect?> {
        val (nextState, effect) = when (intent) {
            is SignInIntent.UpdateIsLoading -> previousState.copy(isLoading = intent.isLoading) to null
            is SignInIntent.UpdateEmail -> previousState.copy(email = intent.email) to null
            is SignInIntent.UpdatePassword -> previousState.copy(password = intent.password) to null
            is SignInIntent.UpdateEmailError -> previousState.copy(emailError = intent.error) to null
            is SignInIntent.UpdatePasswordError -> previousState.copy(passwordError = intent.error) to null
        }
        return nextState.copy(isButtonEnabled = nextState.isButtonEnabled()) to effect
    }

    private fun SignInState.isButtonEnabled() =
        email.isNotEmpty() && password.isNotEmpty() &&
            emailError == null && passwordError == null
}
