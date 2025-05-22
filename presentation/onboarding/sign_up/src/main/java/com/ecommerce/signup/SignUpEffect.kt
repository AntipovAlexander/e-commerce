package com.ecommerce.signup

import androidx.compose.runtime.Immutable
import com.ecommerce.presentation.core.base.Reducer

@Immutable
sealed class SignUpEffect : Reducer.ViewEffect {
    data object SignedUp : SignUpEffect()
}
