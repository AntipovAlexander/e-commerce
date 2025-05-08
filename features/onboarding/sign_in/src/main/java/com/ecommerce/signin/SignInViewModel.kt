package com.ecommerce.signin

import com.ecommerce.core.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor() :
    BaseViewModel<SignInState, SignInIntents, SignInEffects>(
        initialState = SignInState(isLoading = false),
        reducer = SignInStateReducer()
    )
