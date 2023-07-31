package com.space.etozheluka_paging.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.space.etozheluka_paging.domain.use_case.GetMoviesUseCase
import com.space.etozheluka_paging.model.MoviesDTO
import kotlinx.coroutines.flow.Flow

private const val ITEMS_PER_PAGE = 20

class MainViewModel(private val getMoviesUseCase: GetMoviesUseCase) :
    ViewModel() {

    fun fetchMovies(): Flow<PagingData<MoviesDTO.Result>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
            pagingSourceFactory = {
                getMoviesUseCase.invoke()
            }
        ).flow
            // cachedIn allows paging to remain active in the viewModel scope, so even if the UI
            // showing the paged data goes through lifecycle changes, pagination remains cached and
            // the UI does not have to start paging from the beginning when it resumes.
            .cachedIn(viewModelScope)
    }
}