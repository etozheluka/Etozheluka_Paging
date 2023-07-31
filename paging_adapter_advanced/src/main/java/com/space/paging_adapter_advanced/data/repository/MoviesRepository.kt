package com.space.paging_adapter_advanced.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.space.paging_adapter_advanced.data.local.MoviesDatabase
import com.space.paging_adapter_advanced.data.models.Movie
import com.space.paging_adapter_advanced.data.remote.MoviesApiService
import com.space.paging_adapter_advanced.data.remote.MoviesRemoteMediator
import kotlinx.coroutines.flow.Flow

const val PAGE_SIZE = 20

class MoviesRepository(
    private val moviesApiService: MoviesApiService,
    private val moviesDatabase: MoviesDatabase
) {

    @OptIn(ExperimentalPagingApi::class)
    fun getPopularMovies(): Flow<PagingData<Movie>> =
        Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                prefetchDistance = 10,
                initialLoadSize = PAGE_SIZE, // How many items you want to load initially
            ),
            pagingSourceFactory = {
                // The pagingSourceFactory lambda should always return a brand new PagingSource
                // when invoked as PagingSource instances are not reusable.
                moviesDatabase.getMoviesDao().getMovies()
            },
            remoteMediator = MoviesRemoteMediator(
                moviesApiService,
                moviesDatabase,
            )
        ).flow
}