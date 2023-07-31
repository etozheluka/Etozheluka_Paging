package com.space.etozheluka_paging.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.space.etozheluka_paging.data.remote.repository.MoviesPagingSource
import com.space.etozheluka_paging.domain.use_case.GetMoviesUseCase
import com.space.etozheluka_paging.model.MoviesDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

private const val ITEMS_PER_PAGE = 20

class MainViewModel(private val getMoviesUseCase: GetMoviesUseCase) :
    ViewModel() {

    private val _movies = MutableLiveData<MoviesPagingSource>()
    val movies: LiveData<MoviesPagingSource>
        get() = _movies

    init {
        getMovies()
    }

    private fun getMovies() {
        viewModelScope.launch {
            _movies.value = getMoviesUseCase.invoke()
        }
    }

    fun fetchMovies(): Flow<PagingData<MoviesDTO.Result>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
            pagingSourceFactory = {
                _movies.value!!
            }
        ).flow
            // cachedIn allows paging to remain active in the viewModel scope, so even if the UI
            // showing the paged data goes through lifecycle changes, pagination remains cached and
            // the UI does not have to start paging from the beginning when it resumes.
            .cachedIn(viewModelScope)
    }
}