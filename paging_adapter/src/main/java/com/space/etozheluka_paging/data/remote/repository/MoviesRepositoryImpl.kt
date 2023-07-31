package com.space.etozheluka_paging.data.remote.repository

import com.space.etozheluka_paging.data.remote.service.RequestHandler
import com.space.etozheluka_paging.data.remote.service.ServiceApi
import com.space.etozheluka_paging.domain.repository.MoviesRepository

class MoviesRepositoryImpl(
    private val serviceApi: ServiceApi
) :
    RequestHandler(),
    MoviesRepository {

    override fun moviePagingSource(): MoviesPagingSource = MoviesPagingSource(serviceApi)
}