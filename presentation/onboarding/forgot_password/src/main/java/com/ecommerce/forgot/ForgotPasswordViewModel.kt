package com.ecommerce.forgot

import androidx.lifecycle.viewModelScope
import com.ecommerce.domain.core.base.getOrNull
import com.ecommerce.domain.core.validate.ValidateEmailUseCase
import com.ecommerce.presentation.core.base.BaseViewModel
import com.ecommerce.presentation.core.extensions.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase
) : BaseViewModel<ForgotPasswordState, ForgotPasswordIntent, ForgotPasswordEffect>(
    initialState = ForgotPasswordState.default(),
    reducer = ForgotPasswordStateReducer()
) {

    fun onEmailUpdate(email: String) {
        applyIntent(ForgotPasswordIntent.UpdateEmail(email))
        viewModelScope.launch {
            val errorMessage = validateEmailUseCase(ValidateEmailUseCase.Params(email)).getOrNull()
            applyIntent(ForgotPasswordIntent.UpdateEmailError(errorMessage))
        }
    }

    @Suppress("MagicNumber")
    fun onRestorePasswordClick() {
        applyIntent(ForgotPasswordIntent.UpdateIsLoading(true))
        viewModelScope.launch {
            // TODO make replace with use case
            kotlinx.coroutines.delay(1000)
            applyEffect(ForgotPasswordEffect.EmailSent)
            applyIntent(ForgotPasswordIntent.UpdateIsLoading(false))
        }
    }
}
