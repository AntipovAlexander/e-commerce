package com.ecommerce.data.core.error

import com.ecommerce.data.core.R
import com.ecommerce.data.core.model.ErrorModel
import com.ecommerce.data.core.network.CustomClientErrorException
import com.ecommerce.domain.core.error.ErrorResourceManager
import com.ecommerce.domain.core.resources.ResourceProvider
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorResourceManagerImpl @Inject constructor(resourceProvider: ResourceProvider) : ErrorResourceManager {

    private val defaultErrorMessage by lazy { resourceProvider.getString(R.string.default_error) }
    private val noInternetMessage by lazy { resourceProvider.getString(R.string.no_internet_error) }

    override suspend fun getErrorMessage(error: Throwable): String {
        val message = when (error) {
            is ClientRequestException -> error.response.body<ErrorModel>().message
            is CustomClientErrorException -> error.body.message
            is UnknownHostException -> noInternetMessage
            else -> null
        }
        return message ?: error.localizedMessage ?: defaultErrorMessage
    }
}
