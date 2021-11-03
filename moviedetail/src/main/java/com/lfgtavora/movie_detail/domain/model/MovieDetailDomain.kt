package com.lfgtavora.movie_detail.domain.model

data class MovieDetailDomain(
    val title: String,
    val id: String,
    val description: String,
    val releaseDate: CustomDate?,
    val cover: String,
    val poster: String,
    val homepage: String,
    val rating: Double,
    val originalTitle: String,
    val adult: Boolean,
    val companies: List<Company>,
    val genres: List<Genre>
)

data class CustomDate (
    val year: String,
    val month: String,
    val day: String
)

data class Genre (
    val id: Long,
    val name: String,
)

data class Company(
    val id: Long,
    val logo: String,
    val name: String
)