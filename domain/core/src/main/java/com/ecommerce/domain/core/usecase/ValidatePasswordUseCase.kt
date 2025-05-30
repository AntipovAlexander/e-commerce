package com.ecommerce.domain.core.usecase

import androidx.core.text.trimmedLength
import com.ecommerce.domain.core.R
import com.ecommerce.domain.core.base.UseCase
import com.ecommerce.domain.core.resources.ResourceProvider
import javax.inject.Inject

class ValidatePasswordUseCase @Inject constructor(
    private val resourceProvider: ResourceProvider
) : UseCase<ValidatePasswordUseCase.Params, String?>() {

    override suspend fun executeOnBackground(params: Params): String? {
        val isValid = params.password.trimmedLength() >= MIN_PASSWORD_LENGTH
        val errorMessage = when (isValid) {
            false -> resourceProvider.getQuantityString(
                resourceIdentifier = R.plurals.password_error_text,
                quantity = MIN_PASSWORD_LENGTH,
                MIN_PASSWORD_LENGTH
            )

            true -> null
        }
        return errorMessage
    }

    data class Params(val password: String)

    companion object {
        private const val MIN_PASSWORD_LENGTH = 6
    }
}
