package com.ecommerce.domain.auth.usecase

import com.ecommerce.domain.auth.repository.AuthRepository
import com.ecommerce.domain.core.base.UseCase
import javax.inject.Inject

class RestorePasswordUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<RestorePasswordUseCase.Params, Unit>() {

    override suspend fun executeOnBackground(params: Params): Unit = authRepository.restorePassword(params.email)

    data class Params(val email: String)
}
