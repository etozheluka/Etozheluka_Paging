package com.space.etozheluka_paging.domain.repository


import com.space.etozheluka_paging.data.remote.repository.MoviesPagingSource


/**
 * This interface is used to define the methods that will be used to get the movies from the API
 */
interface MoviesRepository {
    suspend fun moviePagingSource(): MoviesPagingSource
}