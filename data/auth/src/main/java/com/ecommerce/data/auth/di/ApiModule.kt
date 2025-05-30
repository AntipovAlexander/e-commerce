package com.ecommerce.data.auth.di

import com.ecommerce.data.auth.api.AuthApiDataSource
import com.ecommerce.data.auth.sources.AuthDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ApiModule {

    @Binds
    fun bindAuthApi(api: AuthApiDataSource): AuthDataSource
}
