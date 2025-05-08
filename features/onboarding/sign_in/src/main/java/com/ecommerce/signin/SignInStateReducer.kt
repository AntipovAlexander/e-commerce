package com.ecommerce.signin

import com.ecommerce.core.ui.base.Reducer

class SignInStateReducer : Reducer<SignInState, SignInIntents, SignInEffects> {
    override fun reduce(
        previousState: SignInState,
        intent: SignInIntents
    ): Pair<SignInState, SignInEffects?> {
        TODO("Not yet implemented")
    }
}
