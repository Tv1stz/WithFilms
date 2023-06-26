package com.example.withfilms.di

import com.example.withfilms.data.RepositoryImpl
import com.example.withfilms.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideRepository(
        repository: RepositoryImpl
    ): Repository

}