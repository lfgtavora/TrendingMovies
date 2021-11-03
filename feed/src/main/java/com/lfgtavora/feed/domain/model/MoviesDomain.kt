package com.lfgtavora.feed.domain.model

import java.util.*

data class MoviesDomain(
    val movies: List<MovieDomain>?
)

data class MovieDomain(
    val id: Int,
    val title: String?,
    val poster: String,
    val rating: Double,
    val releaseDate: Date?,
    val cover: String,
)