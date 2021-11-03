package com.lfgtavora.feed.domain.usecase

import com.lfgtavora.feed.domain.model.MovieDomain
import com.lfgtavora.feed.domain.repository.IMovieRepository
import com.lfgtavora.feed.domain.repository.MoviesRepositoryImpl
import kotlinx.coroutines.flow.Flow

class MoviesUseCaseImpl(private val repository: IMovieRepository) : IMoviesUsecase {
    override suspend fun getPopularMovies(page: Int, language: String?): Flow<List<MovieDomain>> =
        repository.getPopularMovies(page, language)

    override suspend fun getLatestMovies(language: String?): Flow<List<MovieDomain>> =
        repository.getLatestMovies( language)

}