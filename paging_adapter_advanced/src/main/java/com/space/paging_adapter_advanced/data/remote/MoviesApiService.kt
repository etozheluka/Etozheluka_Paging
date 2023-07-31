package com.space.paging_adapter_advanced.data.remote

import com.space.paging_adapter_advanced.data.models.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MoviesApiService {
    @GET("movie/popular")
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZTIwM2Y3YmM1MGMzN2I3ZjA0ODhlOGFjN2E0NGFiNSIsInN1YiI6IjY0YjY3NDMxZTBjYTdmMDEyNTNlZjQxZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.8HQi99ULMt91sFBhcp0UK78PJIasGD0si8H0XVchFjs")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): MovieResponse
}