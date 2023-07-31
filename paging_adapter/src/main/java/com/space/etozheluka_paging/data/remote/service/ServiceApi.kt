package com.space.etozheluka_paging.data.remote.service

import com.space.etozheluka_paging.model.MoviesDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * This interface is used to define the methods that will be used to get the movies from the API
 */
interface ServiceApi {

    @GET("movie/popular")
    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJkZTIwM2Y3YmM1MGMzN2I3ZjA0ODhlOGFjN2E0NGFiNSIsInN1YiI6IjY0YjY3NDMxZTBjYTdmMDEyNTNlZjQxZCIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.8HQi99ULMt91sFBhcp0UK78PJIasGD0si8H0XVchFjs")
    suspend fun getResponse(@Query("page") page: Int): Response<MoviesDTO>
}