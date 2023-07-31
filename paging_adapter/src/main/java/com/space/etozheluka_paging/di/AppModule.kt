package com.space.etozheluka_paging.di

import com.space.etozheluka_paging.data.remote.repository.MoviesPagingSource
import com.space.etozheluka_paging.data.remote.repository.MoviesRepositoryImpl
import com.space.etozheluka_paging.data.remote.service.ServiceApi
import com.space.etozheluka_paging.domain.repository.MoviesRepository
import com.space.etozheluka_paging.domain.use_case.GetMoviesUseCase
import com.space.etozheluka_paging.ui.MainViewModel
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/**
 * This class is used to provide the dependencies that will be used in the application.
 */

val apiModule = module {
    single {
        Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(
                MoshiConverterFactory.create(get())
            )
            .build()
    }
    single {
        provideService(get())
    }

    single {
        MoviesPagingSource(get())
    }

    single { GetMoviesUseCase(get()) }

    viewModel { MainViewModel(get()) }

    single<MoviesRepository> { MoviesRepositoryImpl(get()) }

}

fun provideService(retrofit: Retrofit): ServiceApi =
    retrofit.create(ServiceApi::class.java)