package com.space.manual_paging.domain.use_case

import com.space.manual_paging.domain.repository.MoviesRepository
import javax.inject.Inject

class GetMoviesUseCase @Inject constructor(private val repository: MoviesRepository) {

    suspend fun invoke(page: Int) = repository.getMovieList(page)
}