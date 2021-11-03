package com.lfgtavora.feed.domain.usecase

import com.lfgtavora.feed.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

interface IMoviesUsecase {
    suspend fun getPopularMovies(page: Int = 1, language: String? = "pt-BR"): Flow<List<MovieDomain>>
    suspend fun getLatestMovies(language: String? = "pt-BR"): Flow<List<MovieDomain>>
}