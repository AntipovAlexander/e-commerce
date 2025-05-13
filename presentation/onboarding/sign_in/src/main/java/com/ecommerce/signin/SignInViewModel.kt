package com.ecommerce.signin

import androidx.lifecycle.viewModelScope
import com.ecommerce.domain.core.base.getOrNull
import com.ecommerce.domain.core.validate.ValidateEmailUseCase
import com.ecommerce.domain.core.validate.ValidatePasswordUseCase
import com.ecommerce.presentation.core.base.BaseViewModel
import com.ecommerce.presentation.core.extensions.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
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
}
