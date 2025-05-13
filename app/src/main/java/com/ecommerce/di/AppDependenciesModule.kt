package com.ecommerce.di

import android.content.Context
import com.ecommerce.data.core.resources.AndroidResourceProvider
import com.ecommerce.domain.core.resources.ResourceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppDependenciesModule {

    @Provides
    @Singleton
    fun provideResourcesProvider(@ApplicationContext context: Context): ResourceProvider =
        AndroidResourceProvider(context)
}
