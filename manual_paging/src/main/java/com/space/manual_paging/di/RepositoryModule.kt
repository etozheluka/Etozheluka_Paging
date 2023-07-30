package com.space.manual_paging.di

import com.space.manual_paging.data.remote.repository.MoviesRepositoryImpl
import com.space.manual_paging.domain.repository.MoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * This class is used to provide the dependencies that will be used in the app
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideMovieRepository(
        repositoryImpl: MoviesRepositoryImpl
    ): MoviesRepository
}