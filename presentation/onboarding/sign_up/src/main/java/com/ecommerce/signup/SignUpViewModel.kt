package com.ecommerce.signup

import androidx.lifecycle.viewModelScope
import com.ecommerce.domain.auth.usecase.SignUpUseCase
import com.ecommerce.domain.core.base.doOnError
import com.ecommerce.domain.core.base.doOnSuccess
import com.ecommerce.domain.core.base.finally
import com.ecommerce.domain.core.base.getOrNull
import com.ecommerce.domain.core.error.ErrorResourceManager
import com.ecommerce.domain.core.resources.ResourceProvider
import com.ecommerce.domain.core.usecase.ValidateEmailUseCase
import com.ecommerce.domain.core.usecase.ValidatePasswordUseCase
import com.ecommerce.presentation.core.base.BaseViewModel
import com.ecommerce.presentation.core.extensions.launch
import com.ecommerce.presentation.core.model.NotificationMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val signUpUseCase: SignUpUseCase,
    private val resourceProvider: ResourceProvider,
    private val errorResourceManager: ErrorResourceManager
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

    fun onSignUpClick() = viewModelScope.launch {
        val params = SignUpUseCase.Params(email = state.value.email, password = state.value.repeatedPassword)
        applyIntent(SignUpIntent.UpdateIsLoading(true))
        signUpUseCase(params)
            .doOnSuccess {
                val message = resourceProvider.getString(R.string.sign_up_success)
                applyEffect(SignUpEffect.ShowMessage(NotificationMessage.Success(message)))
                applyEffect(SignUpEffect.SignUpSuccess)
            }
            .doOnError {
                val message = errorResourceManager.getErrorMessage(it)
                applyEffect(SignUpEffect.ShowMessage(NotificationMessage.Error(message)))
            }
            .finally { applyIntent(SignUpIntent.UpdateIsLoading(false)) }
    }
}
