package com.ecommerce.signin

import androidx.compose.runtime.Immutable
import com.ecommerce.presentation.core.base.Reducer
import com.ecommerce.presentation.core.model.NotificationMessage

@Immutable
sealed class SignInEffect : Reducer.ViewEffect {
    object NavigateToHome : SignInEffect()
    data class ShowMessage(val error: NotificationMessage) : SignInEffect()
}
