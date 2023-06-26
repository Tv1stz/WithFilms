package com.example.withfilms.di

import com.example.withfilms.domain.repository.Repository
import com.example.withfilms.domain.usecases.GetFilmDetailByIdUseCase
import com.example.withfilms.domain.usecases.GetFilmStaffByIdUseCase
import com.example.withfilms.domain.usecases.GetPersonDetailByIdUseCase
import com.example.withfilms.domain.usecases.GetSearchFilmsByKeyWordUseCase
import com.example.withfilms.domain.usecases.GetTopFilmsUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun provideGetFilmDetailByIdUseCase(repository: Repository) =
        GetFilmDetailByIdUseCase(repository)

    @Singleton
    @Provides
    fun provideGetFilmStaffByIdUseCase(repository: Repository) =
        GetFilmStaffByIdUseCase(repository)

    @Singleton
    @Provides
    fun provideGetPersonDetailByIdUseCase(repository: Repository) =
        GetPersonDetailByIdUseCase(repository)

    @Singleton
    @Provides
    fun provideGetSearchFilmsByKeyWordUseCase(repository: Repository) =
        GetSearchFilmsByKeyWordUseCase(repository)

    @Singleton
    @Provides
    fun provideGetTopFilmsUseCase(repository: Repository) =
        GetTopFilmsUseCase(repository)
}