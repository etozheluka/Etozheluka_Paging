package com.space.manual_paging.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.space.manual_paging.data.remote.service.Resource
import com.space.manual_paging.domain.use_case.GetMoviesUseCase
import com.space.manual_paging.model.MoviesDTO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val getMoviesUseCase: GetMoviesUseCase) :
    ViewModel() {

    private val _movies = MutableLiveData<Resource<MoviesDTO>>()
    val movies: LiveData<Resource<MoviesDTO>>
        get() = _movies

    private var currentPage = 0

    init {
        getNextPage()
    }

    /**
     * This method is used to get the movies from the use case
     */
    fun getNextPage() {
        viewModelScope.launch {
            currentPage++
            val newMovies = getMoviesUseCase.invoke(currentPage)
            _movies.value = newMovies
        }
    }

    fun getPrevPage() {
        viewModelScope.launch {
            currentPage--
            val newMovies = getMoviesUseCase.invoke(currentPage)
            _movies.value = newMovies
        }
    }
}