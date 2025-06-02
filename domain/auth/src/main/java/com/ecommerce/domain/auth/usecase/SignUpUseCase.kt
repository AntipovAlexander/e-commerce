package com.ecommerce.domain.auth.usecase

import com.ecommerce.domain.auth.repository.AuthRepository
import com.ecommerce.domain.core.base.UseCase
import javax.inject.Inject

class SignUpUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<SignUpUseCase.Params, Unit>() {

    override suspend fun executeOnBackground(params: Params): Unit =
        authRepository.signUp(params.email, params.password)

    data class Params(val email: String, val password: String)
}
