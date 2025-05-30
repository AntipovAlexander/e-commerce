package com.ecommerce.data.core.di

import com.ecommerce.data.core.error.ErrorResourceManagerImpl
import com.ecommerce.domain.core.error.ErrorResourceManager
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface ErrorModule {

    @Binds
    @Singleton
    fun bindErrorResourceManager(errorResourceManagerImpl: ErrorResourceManagerImpl): ErrorResourceManager
}
