package com.ecommerce.domain.core.validate

import com.ecommerce.domain.core.R
import com.ecommerce.domain.core.base.UseCase
import com.ecommerce.domain.core.resources.ResourceProvider
import javax.inject.Inject

class ValidateEmailUseCase @Inject constructor(
    private val resourceProvider: ResourceProvider
) : UseCase<ValidateEmailUseCase.Params, String?>() {

    @Suppress("MaximumLineLength", "MaxLineLength")
    private val emailRegex = Regex(
        pattern = """(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])""",
    )

    override suspend fun executeOnBackground(params: Params): String? {
        val isValid = emailRegex.matches(params.email)
        val errorMessage = when (isValid) {
            false -> resourceProvider.getString(R.string.email_error_text)
            true -> null
        }
        return errorMessage
    }

    data class Params(val email: String)
}
