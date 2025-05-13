package com.ecommerce.domain.core.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

abstract class FlowUseCase<in Params, out ResultType>(
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO
) where Params : Any? {

    protected abstract fun executeOnBackground(params: Params): Flow<ResultType>

    open operator fun invoke(params: Params): Flow<Either<Exception, ResultType>> =
        executeOnBackground(params)
            .flowOn(coroutineDispatcher)
            .map { Either.Right(it) }
}
