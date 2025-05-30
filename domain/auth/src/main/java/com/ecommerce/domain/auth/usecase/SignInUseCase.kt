package com.ecommerce.domain.auth.usecase

import com.ecommerce.domain.auth.model.User
import com.ecommerce.domain.auth.repository.AuthRepository
import com.ecommerce.domain.core.base.UseCase
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val authRepository: AuthRepository
) : UseCase<SignInUseCase.Params, User>() {

    // https://dummyjson.com/users
    private val emailToUsername = mapOf(
        "emily.johnson@x.dummyjson.com" to "emilys",
        "michael.williams@x.dummyjson.com" to "michaelw",
        "sophia.brown@x.dummyjson.com" to "sophiab",
        "james.davis@x.dummyjson.com" to "jamesd",
        "emma.miller@x.dummyjson.com" to "emmaj",
        "olivia.wilson@x.dummyjson.com" to "oliviaw",
        "alexander.jones@x.dummyjson.com" to "alexanderj",
        "ava.taylor@x.dummyjson.com" to "avat",
        "ethan.martinez@x.dummyjson.com" to "ethanm",
        "isabella.anderson@x.dummyjson.com" to "isabellad",
        "liam.garcia@x.dummyjson.com" to "liamg",
        "mia.rodriguez@x.dummyjson.com" to "miar",
        "noah.hernandez@x.dummyjson.com" to "noahh",
        "charlotte.lopez@x.dummyjson.com" to "charlottem",
        "william.gonzalez@x.dummyjson.com" to "williamg",
        "avery.perez@x.dummyjson.com" to "averyp",
        "evelyn.sanchez@x.dummyjson.com" to "evelyns",
        "logan.torres@x.dummyjson.com" to "logant",
        "abigail.rivera@x.dummyjson.com" to "abigailr",
        "jackson.evans@x.dummyjson.com" to "jacksone",
        "madison.collins@x.dummyjson.com" to "madisonc",
        "elijah.stewart@x.dummyjson.com" to "elijahs",
        "chloe.morales@x.dummyjson.com" to "chloem",
        "mateo.nguyen@x.dummyjson.com" to "mateon",
        "harper.kelly@x.dummyjson.com" to "harpere",
        "evelyn.gonzalez@x.dummyjson.com" to "evelyng",
        "daniel.cook@x.dummyjson.com" to "danielc",
        "lily.lee@x.dummyjson.com" to "lilyb",
        "henry.hill@x.dummyjson.com" to "henryh",
        "addison.wright@x.dummyjson.com" to "addisonw"
    )

    override suspend fun executeOnBackground(params: Params): User = authRepository.signIn(
        username = emailToUsername[params.email] ?: "",
        password = params.password
    )

    data class Params(
        val email: String,
        val password: String
    )
}
