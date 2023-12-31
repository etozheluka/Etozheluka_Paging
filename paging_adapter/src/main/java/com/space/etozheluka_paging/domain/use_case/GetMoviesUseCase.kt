package com.space.etozheluka_paging.domain.use_case

import com.space.etozheluka_paging.domain.repository.MoviesRepository


class GetMoviesUseCase(private val repository: MoviesRepository) {

    fun invoke() = repository.moviePagingSource()
}