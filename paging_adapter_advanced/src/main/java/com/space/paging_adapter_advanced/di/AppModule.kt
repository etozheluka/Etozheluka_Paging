package com.space.paging_adapter_advanced.di

import android.content.Context
import androidx.room.Room
import com.space.paging_adapter_advanced.data.local.MoviesDatabase
import com.space.paging_adapter_advanced.data.remote.MoviesApiService
import com.space.paging_adapter_advanced.data.remote.MoviesRemoteMediator
import com.space.paging_adapter_advanced.data.repository.MoviesRepository
import com.space.paging_adapter_advanced.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * This class is used to provide the dependencies that will be used in the application.
 */

val apiModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }
    single {
        provideService(get())
    }

    viewModel { MainViewModel(get()) }


    single { provideUserDataBase(get()) }
    single { provideDao(get()) }
    single { provideKeyDao(get()) }
    single { MoviesRemoteMediator(get(), get()) }
    single { MoviesRepository(get(), get()) }

}

private fun provideUserDataBase(context: Context): MoviesDatabase {
    return Room.databaseBuilder(context, MoviesDatabase::class.java, "movies_database")
        .enableMultiInstanceInvalidation()
        .fallbackToDestructiveMigration().build()
}

private fun provideDao(db: MoviesDatabase) = db.getMoviesDao()

private fun provideKeyDao(db: MoviesDatabase) = db.getRemoteKeysDao()

fun provideService(retrofit: Retrofit): MoviesApiService =
    retrofit.create(MoviesApiService::class.java)