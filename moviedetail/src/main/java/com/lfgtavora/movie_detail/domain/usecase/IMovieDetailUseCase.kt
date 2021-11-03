package com.lfgtavora.movie_detail.domain.usecase

import com.lfgtavora.movie_detail.domain.model.MovieDetailDomain
import kotlinx.coroutines.flow.Flow

interface IMovieDetailUseCase {
    suspend fun getMovieDetail(id: String, language: String? = "pt-BR"): Flow<MovieDetailDomain>
}