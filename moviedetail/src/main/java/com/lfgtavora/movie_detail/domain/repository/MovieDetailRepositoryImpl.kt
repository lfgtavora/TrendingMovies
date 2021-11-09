package com.lfgtavora.movie_detail.domain.repository

import com.lfgtavora.movie_detail.data.mapper.MovieDetailMapper
import com.lfgtavora.movie_detail.data.remote.model.MovieDetailResponse
import com.lfgtavora.movie_detail.domain.model.MovieDetailDomain
import com.lfgtavora.networking.TMDB_BASE_URL
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieDetailRepositoryImpl(
    private val httpClient: HttpClient,
    private val movieDetailMapper: MovieDetailMapper
) {
    suspend fun getMovieDetail(id: String, language: String? = null): Flow<MovieDetailDomain> =
        flow {
            val response =
                httpClient.get<MovieDetailResponse>("$TMDB_BASE_URL/movie/$id?language=$language")
            emit(movieDetailMapper.toDomain(response))
        }
}