package com.ecommerce.signup

import com.ecommerce.presentation.core.base.Reducer

internal class SignUpStateReducer : Reducer<SignUpState, SignUpIntent, SignUpEffect> {
    override fun reduce(
        previousState: SignUpState,
        intent: SignUpIntent
    ): Pair<SignUpState, SignUpEffect?> {
        val nextState = when (intent) {
            is SignUpIntent.UpdateEmail -> previousState.copy(email = intent.email)
            is SignUpIntent.UpdatePassword -> previousState.copy(password = intent.password)
            is SignUpIntent.UpdateRepeatedPassword -> previousState.copy(repeatedPassword = intent.password)
            is SignUpIntent.UpdateEmailError -> previousState.copy(emailError = intent.error)
            is SignUpIntent.UpdatePasswordError -> previousState.copy(passwordError = intent.error)
            is SignUpIntent.UpdateRepeatedPasswordError -> previousState.copy(repeatedPasswordError = intent.error)
            is SignUpIntent.UpdateIsLoading -> previousState.copy(isLoading = intent.isLoading)
        }
        return nextState.copy(isButtonEnabled = nextState.isButtonEnabled()) to null
    }

    private fun SignUpState.isButtonEnabled() = email.isNotEmpty() &&
        password.isNotEmpty() &&
        repeatedPassword.isNotEmpty() &&
        emailError == null &&
        passwordError == null &&
        repeatedPasswordError == null &&
        password == repeatedPassword
}
