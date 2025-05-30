package com.ecommerce.data.core.error

import com.ecommerce.data.core.R
import com.ecommerce.data.core.model.ErrorModel
import com.ecommerce.domain.core.error.ErrorResourceManager
import com.ecommerce.domain.core.resources.ResourceProvider
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import javax.inject.Inject

class ErrorResourceManagerImpl @Inject constructor(resourceProvider: ResourceProvider) : ErrorResourceManager {

    private val defaultErrorMessage by lazy { resourceProvider.getString(R.string.default_error) }

    override suspend fun getErrorMessage(error: Throwable): String {
        val body = (error as? ClientRequestException)?.response?.body<ErrorModel>()
        return body?.message ?: defaultErrorMessage
    }
}
