package com.ecommerce.signup

import androidx.compose.runtime.Immutable
import com.ecommerce.presentation.core.base.Reducer
import com.ecommerce.presentation.core.model.NotificationMessage

@Immutable
sealed class SignUpEffect : Reducer.ViewEffect {
    data object SignUpSuccess : SignUpEffect()
    data class ShowMessage(val message: NotificationMessage) : SignUpEffect()
}
