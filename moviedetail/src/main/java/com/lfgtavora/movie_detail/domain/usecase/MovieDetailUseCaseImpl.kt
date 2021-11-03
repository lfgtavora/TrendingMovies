package com.lfgtavora.movie_detail.domain.usecase

import com.lfgtavora.movie_detail.domain.model.MovieDetailDomain
import com.lfgtavora.movie_detail.domain.repository.MovieDetailRepositoryImpl
import kotlinx.coroutines.flow.Flow

class MovieDetailUseCaseImpl(private val movieDetailRepositoryImpl: MovieDetailRepositoryImpl) :
    IMovieDetailUseCase {

    override suspend fun getMovieDetail(id: String, language: String?): Flow<MovieDetailDomain> =
        movieDetailRepositoryImpl.getMovieDetail(id, language)

}
