package com.ecommerce.forgot

import androidx.compose.runtime.Immutable
import com.ecommerce.presentation.core.base.Reducer

@Immutable
sealed class ForgotPasswordEffect : Reducer.ViewEffect {
    data object EmailSent : ForgotPasswordEffect()
}
