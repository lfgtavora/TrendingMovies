package com.lfgtavora.feed.data.mapper

import com.lfgtavora.feed.data.remote.model.MoviesResponse
import com.lfgtavora.feed.domain.model.MovieDomain
import com.lfgtavora.networking.mapper.BaseMapper
import java.util.*

class MoviesMapper : BaseMapper.ToDomain<MoviesResponse, List<MovieDomain>> {
    override fun toDomain(entity: MoviesResponse): List<MovieDomain> =
        entity.results.map { item ->
            MovieDomain(
                id = item.id,
                title = item.title,
                poster = item.posterPath,
                cover = item.backdropPath,
                rating = item.voteAverage,
                releaseYear = convertToCustomDate(item.releaseDate)
            )
        }


    private fun convertToCustomDate(stringDate: String?): String? =
        stringDate?.let {
            stringDate.substringBefore("-")
        }
}