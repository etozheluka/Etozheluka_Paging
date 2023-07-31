package com.space.paging_adapter_advanced.presentation

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.space.paging_adapter_advanced.data.models.Movie
import com.space.paging_adapter_advanced.data.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow


class MainViewModel(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    fun getPopularMovies(): Flow<PagingData<Movie>> =
        moviesRepository.getPopularMovies()
}