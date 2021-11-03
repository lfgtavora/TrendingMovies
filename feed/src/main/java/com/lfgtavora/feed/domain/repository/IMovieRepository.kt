package com.lfgtavora.feed.domain.repository

import com.lfgtavora.feed.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {
    suspend fun getPopularMovies(page: Int, language: String? = null): Flow<List<MovieDomain>>
    suspend fun getLatestMovies(language: String?): Flow<List<MovieDomain>>
}
