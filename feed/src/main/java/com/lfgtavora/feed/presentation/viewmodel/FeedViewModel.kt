package com.lfgtavora.feed.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lfgtavora.feed.domain.model.MovieDomain
import com.lfgtavora.feed.domain.usecase.IMoviesUsecase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FeedViewModel(private val useCase: IMoviesUsecase) : ViewModel() {

    private val _popularMoviesState = mutableStateOf(UiState())
    val popularMoviesState: State<UiState> = _popularMoviesState

    private val _recentsMoviesState = mutableStateOf(UiState())
    val recentsMoviesState: State<UiState> = _recentsMoviesState

    init {
        getLatestMovies()
        getPopularMovies()
    }

    internal fun getPopularMovies() {
        viewModelScope.launch {
            _popularMoviesState.value =
                _popularMoviesState.value.copy(isLoading = true, error = null)
            useCase.getPopularMovies(1)
                .catch {
                    _popularMoviesState.value = _popularMoviesState.value.copy(
                        error = UiState.Error.NetworkError,
                        isLoading = false
                    )
                }
                .collect { movies ->
                    _popularMoviesState.value =
                        _popularMoviesState.value.copy(items = movies, isLoading = false)
                }
        }
    }

    internal fun getLatestMovies() {
        viewModelScope.launch {
            _popularMoviesState.value =
                _popularMoviesState.value.copy(isLoading = true, error = null)
            useCase.getLatestMovies()
                .catch {
                    _recentsMoviesState.value = _recentsMoviesState.value.copy(
                        error = UiState.Error.NetworkError,
                        isLoading = false
                    )
                }
                .collect { movies ->
                    _recentsMoviesState.value =
                        _recentsMoviesState.value.copy(items = movies, isLoading = false)
                }
        }
    }
}

data class UiState(
    val isLoading: Boolean = false,
    val error: Error? = null,
    val items: List<MovieDomain> = listOf()
) {
    sealed class Error {
        object NetworkError : Error()
    }
}