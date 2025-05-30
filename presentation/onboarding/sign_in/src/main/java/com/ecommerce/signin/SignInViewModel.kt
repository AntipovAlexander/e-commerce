package com.ecommerce.signin

import androidx.lifecycle.viewModelScope
import com.ecommerce.domain.auth.usecase.SignInUseCase
import com.ecommerce.domain.core.base.doOnError
import com.ecommerce.domain.core.base.doOnSuccess
import com.ecommerce.domain.core.base.finally
import com.ecommerce.domain.core.base.getOrNull
import com.ecommerce.domain.core.error.ErrorResourceManager
import com.ecommerce.domain.core.usecase.ValidateEmailUseCase
import com.ecommerce.domain.core.usecase.ValidatePasswordUseCase
import com.ecommerce.presentation.core.base.BaseViewModel
import com.ecommerce.presentation.core.extensions.launch
import com.ecommerce.presentation.core.model.NotificationMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val signInUseCase: SignInUseCase,
    private val errorResourceManager: ErrorResourceManager
) : BaseViewModel<SignInState, SignInIntent, SignInEffect>(
    initialState = SignInState.default(),
    reducer = SignInStateReducer()
) {

    fun onEmailUpdate(email: String) {
        applyIntent(SignInIntent.UpdateEmail(email))
        viewModelScope.launch {
            val errorMessage = validateEmailUseCase(ValidateEmailUseCase.Params(email)).getOrNull()
            applyIntent(SignInIntent.UpdateEmailError(errorMessage))
        }
    }

    fun onPasswordUpdate(password: String) {
        applyIntent(SignInIntent.UpdatePassword(password))
        viewModelScope.launch {
            val errorMessage = validatePasswordUseCase(ValidatePasswordUseCase.Params(password)).getOrNull()
            applyIntent(SignInIntent.UpdatePasswordError(errorMessage))
        }
    }

    fun signIn() = viewModelScope.launch {
        val params = SignInUseCase.Params(email = state.value.email, password = state.value.password)
        applyIntent(SignInIntent.UpdateIsLoading(true))
        signInUseCase(params)
            .doOnSuccess { applyEffect(SignInEffect.NavigateToHome) }
            .doOnError {
                val error = errorResourceManager.getErrorMessage(it)
                applyEffect(SignInEffect.ShowMessage(NotificationMessage.Error(error)))
            }
            .finally { applyIntent(SignInIntent.UpdateIsLoading(false)) }
    }
}
