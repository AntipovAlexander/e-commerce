package com.ecommerce.signup

import androidx.lifecycle.viewModelScope
import com.ecommerce.domain.core.base.getOrNull
import com.ecommerce.domain.core.resources.ResourceProvider
import com.ecommerce.domain.core.usecase.ValidateEmailUseCase
import com.ecommerce.domain.core.usecase.ValidatePasswordUseCase
import com.ecommerce.presentation.core.base.BaseViewModel
import com.ecommerce.presentation.core.extensions.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val resourceProvider: ResourceProvider
) : BaseViewModel<SignUpState, SignUpIntent, SignUpEffect>(
    initialState = SignUpState.default(),
    reducer = SignUpStateReducer()
) {

    fun onEmailUpdate(email: String) {
        applyIntent(SignUpIntent.UpdateEmail(email))
        viewModelScope.launch {
            val errorMessage = validateEmailUseCase(ValidateEmailUseCase.Params(email)).getOrNull()
            applyIntent(SignUpIntent.UpdateEmailError(errorMessage))
        }
    }

    fun onPasswordUpdate(password: String) {
        applyIntent(SignUpIntent.UpdatePassword(password))
        viewModelScope.launch {
            val errorMessage = validatePasswordUseCase(ValidatePasswordUseCase.Params(password)).getOrNull()
            applyIntent(SignUpIntent.UpdatePasswordError(errorMessage))
            validateRepeatedPassword()
        }
    }

    fun onRepeatedPasswordUpdate(password: String) {
        applyIntent(SignUpIntent.UpdateRepeatedPassword(password))
        validateRepeatedPassword()
    }

    private fun validateRepeatedPassword() {
        val currentState = state.value
        val hasError = currentState.repeatedPassword.isNotEmpty() &&
            currentState.password != currentState.repeatedPassword
        val error = when (hasError) {
            true -> resourceProvider.getString(R.string.password_do_not_match)
            false -> null
        }
        applyIntent(SignUpIntent.UpdateRepeatedPasswordError(error))
    }

    @Suppress("MagicNumber")
    fun onSignUpClick() {
        applyIntent(SignUpIntent.UpdateIsLoading(true))
        viewModelScope.launch {
            // Todo: Implement actual sign up logic
            kotlinx.coroutines.delay(1000)
            applyEffect(SignUpEffect.SignedUp)
            applyIntent(SignUpIntent.UpdateIsLoading(false))
        }
    }
}
