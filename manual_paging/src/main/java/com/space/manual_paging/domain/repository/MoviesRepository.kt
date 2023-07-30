package com.space.manual_paging.domain.repository


import com.space.manual_paging.data.remote.service.Resource
import com.space.manual_paging.model.MoviesDTO

/**
 * This interface is used to define the methods that will be used to get the movies from the API
 */
interface MoviesRepository {
    suspend fun getMovieList(page: Int): Resource<MoviesDTO>
}