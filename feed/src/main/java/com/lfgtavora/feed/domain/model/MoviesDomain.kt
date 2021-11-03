package com.lfgtavora.feed.domain.model

data class MovieDomain(
    val id: Int,
    val title: String?,
    val poster: String,
    val rating: Double,
    val cover: String,
    val releaseYear: String?,
)