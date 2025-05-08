package com.ecommerce.signin

import androidx.compose.runtime.Immutable
import com.ecommerce.core.ui.base.Reducer

@Immutable
data class SignInState(val isLoading: Boolean) : Reducer.ViewState
