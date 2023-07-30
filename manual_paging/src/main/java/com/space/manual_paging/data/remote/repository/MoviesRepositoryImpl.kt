package com.space.manual_paging.data.remote.repository

import com.space.manual_paging.data.remote.service.RequestHandler
import com.space.manual_paging.data.remote.service.Resource
import com.space.manual_paging.data.remote.service.ServiceApi
import com.space.manual_paging.domain.repository.MoviesRepository
import com.space.manual_paging.model.MoviesDTO
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(private val serviceApi: ServiceApi) :
    RequestHandler(),
    MoviesRepository {

    override suspend fun getMovieList(page: Int): Resource<MoviesDTO> {
        return apiCall { serviceApi.getResponse(page) }
    }
}