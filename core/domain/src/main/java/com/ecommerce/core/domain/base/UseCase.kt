package com.ecommerce.core.domain.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

abstract class UseCase<in Params, out ResultType>(
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) where Params : Any {

    protected abstract suspend fun executeOnBackground(params: Params): ResultType

    open suspend operator fun invoke(params: Params): Either<Exception, ResultType> =
        runCatching { withContext(coroutineDispatcher) { executeOnBackground(params) } }
}
