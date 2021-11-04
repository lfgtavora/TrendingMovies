package com.lfgtavora.movie_detail.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MovieDetailResponse(
    val adult: Boolean,

    @SerialName("backdrop_path")
    val backdropPath: String?,

    @SerialName("belongs_to_collection")
    val belongsToCollection: BelongsToCollection?,

    val budget: Int,
    val genres: List<Genre>,
    val homepage: String?,
    val id: Int,

    @SerialName("imdb_id")
    val imdbID: String,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String,

    val overview: String?,
    val popularity: Double,

    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("production_companies")
    val productionCompanies: List<ProductionCompany>,


    @SerialName("release_date")
    val releaseDate: String,

    val revenue: Long,
    val runtime: Long,

    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("vote_count")
    val voteCount: Long
)

@Serializable
data class BelongsToCollection(
    val id: Int,
    val name: String,

    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("backdrop_path")
    val backdropPath: String?
)

@Serializable
data class Genre(
    val id: Long,
    val name: String
)

@Serializable
data class ProductionCompany(
    val id: Int,

    @SerialName("logo_path")
    val logoPath: String?,

    val name: String,

    @SerialName("origin_country")
    val originCountry: String
)
