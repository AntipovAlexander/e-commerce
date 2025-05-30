package com.ecommerce.signup

import com.ecommerce.presentation.core.base.Reducer

internal class SignUpStateReducer : Reducer<SignUpState, SignUpIntent, SignUpEffect> {
    override fun reduce(previousState: SignUpState, intent: SignUpIntent): Pair<SignUpState, SignUpEffect?> {
        val (nextState, effect) = when (intent) {
            is SignUpIntent.UpdateEmail -> previousState.copy(email = intent.email) to null
            is SignUpIntent.UpdatePassword -> previousState.copy(password = intent.password) to null
            is SignUpIntent.UpdateRepeatedPassword -> previousState.copy(repeatedPassword = intent.password) to null
            is SignUpIntent.UpdateEmailError -> previousState.copy(emailError = intent.error) to null
            is SignUpIntent.UpdatePasswordError -> previousState.copy(passwordError = intent.error) to null
            is SignUpIntent.UpdateIsLoading -> previousState.copy(isLoading = intent.isLoading) to null
            is SignUpIntent.UpdateRepeatedPasswordError ->
                previousState.copy(repeatedPasswordError = intent.error) to null
        }
        return nextState.copy(isButtonEnabled = nextState.isButtonEnabled()) to effect
    }

    private fun SignUpState.isButtonEnabled() = email.isNotEmpty() &&
        password.isNotEmpty() &&
        repeatedPassword.isNotEmpty() &&
        emailError == null &&
        passwordError == null &&
        repeatedPasswordError == null &&
        password == repeatedPassword
}
