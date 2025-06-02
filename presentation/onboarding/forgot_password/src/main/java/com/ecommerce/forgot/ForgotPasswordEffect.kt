package com.ecommerce.forgot

import androidx.compose.runtime.Immutable
import com.ecommerce.presentation.core.base.Reducer
import com.ecommerce.presentation.core.model.NotificationMessage

@Immutable
sealed class ForgotPasswordEffect : Reducer.ViewEffect {
    data object EmailRestoreSuccess : ForgotPasswordEffect()
    data class ShowMessage(val message: NotificationMessage) : ForgotPasswordEffect()
}
