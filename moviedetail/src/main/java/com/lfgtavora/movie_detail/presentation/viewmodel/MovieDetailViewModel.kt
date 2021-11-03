package com.lfgtavora.movie_detail.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lfgtavora.movie_detail.data.mapper.MovieDetailMapper
import com.lfgtavora.movie_detail.domain.model.MovieDetailDomain
import com.lfgtavora.movie_detail.domain.repository.MovieDetailRepositoryImpl
import com.lfgtavora.movie_detail.domain.usecase.IMovieDetailUseCase
import com.lfgtavora.movie_detail.domain.usecase.MovieDetailUseCaseImpl
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val useCase: IMovieDetailUseCase) : ViewModel() {

    private val _movieDetailState = mutableStateOf(UiState())
    val movieDetailState: State<UiState> = _movieDetailState

    internal fun getMovieDetail(id: String) {
        viewModelScope.launch {
            _movieDetailState.value = _movieDetailState.value.copy(isLoading = true)
            useCase.getMovieDetail(id)
                .catch { e ->
                    e.message
                    _movieDetailState.value = _movieDetailState.value.copy(
                        error = UiState.Error.NetworkError,
                        isLoading = false
                    )
                }
                .collect { movie ->
                    _movieDetailState.value = _movieDetailState.value.copy(movie = movie, isLoading = false)
                }
        }
    }

}

data class UiState(
    val isLoading: Boolean = false,
    val error: Error? = null,
    val movie: MovieDetailDomain? = null
) {
    sealed class Error {
        object NetworkError : Error()
    }
}