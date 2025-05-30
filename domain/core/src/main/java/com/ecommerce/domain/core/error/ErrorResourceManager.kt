package com.ecommerce.domain.core.error

interface ErrorResourceManager {
    suspend fun getErrorMessage(error: Throwable): String
}
