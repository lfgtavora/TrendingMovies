package com.lfgtavora.feed.domain.repository

import com.lfgtavora.feed.data.mapper.MoviesMapper
import com.lfgtavora.feed.data.remote.model.MoviesResponse
import com.lfgtavora.feed.domain.model.MovieDomain
import com.lfgtavora.networking.TMDB_BASE_URL
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val httpClient: HttpClient,
    private val moviesMapper: MoviesMapper
) : IMovieRepository {

    override suspend fun getPopularMovies(
        page: Int,
        language: String?
    ): Flow<List<MovieDomain>> =
        flow {
            val response =
                httpClient.get<MoviesResponse>("$TMDB_BASE_URL/movie/popular?page=$page&language=$language")
            emit(moviesMapper.toDomain(response))
        }

    override suspend fun getLatestMovies(language: String?): Flow<List<MovieDomain>> =
        flow {
            val response =
                httpClient.get<MoviesResponse>("$TMDB_BASE_URL/movie/now_playing?&language=$language")
            emit(moviesMapper.toDomain(response))
        }

}
