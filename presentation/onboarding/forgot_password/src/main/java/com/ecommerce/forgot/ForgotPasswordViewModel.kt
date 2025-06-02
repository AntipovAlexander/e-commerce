package com.ecommerce.forgot

import androidx.lifecycle.viewModelScope
import com.ecommerce.domain.auth.usecase.RestorePasswordUseCase
import com.ecommerce.domain.core.base.doOnError
import com.ecommerce.domain.core.base.doOnSuccess
import com.ecommerce.domain.core.base.finally
import com.ecommerce.domain.core.base.getOrNull
import com.ecommerce.domain.core.error.ErrorResourceManager
import com.ecommerce.domain.core.resources.ResourceProvider
import com.ecommerce.domain.core.usecase.ValidateEmailUseCase
import com.ecommerce.presentation.core.base.BaseViewModel
import com.ecommerce.presentation.core.extensions.launch
import com.ecommerce.presentation.core.model.NotificationMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ForgotPasswordViewModel @Inject constructor(
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val restorePasswordUseCase: RestorePasswordUseCase,
    private val errorResourceManager: ErrorResourceManager,
    private val resourceProvider: ResourceProvider
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

    fun onRestorePasswordClick() = viewModelScope.launch {
        applyIntent(ForgotPasswordIntent.UpdateIsLoading(true))
        val params = RestorePasswordUseCase.Params(state.value.email)
        restorePasswordUseCase(params)
            .doOnSuccess {
                val message = resourceProvider.getString(R.string.email_restore_success_message)
                applyEffect(ForgotPasswordEffect.ShowMessage(NotificationMessage.Success(message)))
                applyEffect(ForgotPasswordEffect.EmailRestoreSuccess)
            }
            .doOnError {
                val message = errorResourceManager.getErrorMessage(it)
                applyEffect(ForgotPasswordEffect.ShowMessage(NotificationMessage.Error(message)))
            }
            .finally { applyIntent(ForgotPasswordIntent.UpdateIsLoading(false)) }
    }
}
