package com.ecommerce.signin

import android.util.Patterns
import com.ecommerce.core.domain.resources.ResourceProvider
import com.ecommerce.core.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val resourceProvider: ResourceProvider
) : BaseViewModel<SignInState, SignInIntent, SignInEffect>(
    initialState = SignInState.default(),
    reducer = SignInStateReducer()
) {

    companion object {
        private val PASSWORD_REGEX = Regex("""^(?=.*[A-Z])[A-Za-z]{6,12}$""")
    }

    fun onEmailUpdate(email: String) {
        val isValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        val errorMessage = when (isValid) {
            false -> resourceProvider.getString(R.string.email_error_text)
            true -> null
        }
        applyIntent(SignInIntent.UpdateEmail(email, errorMessage))
    }

    fun onPasswordUpdate(password: String) {
        val isValid = PASSWORD_REGEX.matches(password)
        val errorMessage = when (isValid) {
            false -> resourceProvider.getString(R.string.password_error_text)
            true -> null
        }
        applyIntent(SignInIntent.UpdatePassword(password, errorMessage))
    }
}
