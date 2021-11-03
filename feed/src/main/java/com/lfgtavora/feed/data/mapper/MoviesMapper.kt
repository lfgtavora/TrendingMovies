package com.lfgtavora.feed.data.mapper

import com.lfgtavora.feed.data.remote.model.MoviesResponse
import com.lfgtavora.feed.domain.model.MovieDomain
import com.lfgtavora.networking.mapper.BaseMapper
import java.text.SimpleDateFormat
import java.util.*

class MoviesMapper: BaseMapper.ToDomain<MoviesResponse, List<MovieDomain>>  {
    override fun toDomain(entity: MoviesResponse): List<MovieDomain> =
        entity.results.map { item ->
            MovieDomain(
                id = item.id,
                title = item.title,
                poster = item.posterPath,
                cover = item.backdropPath,
                rating = item.voteAverage,
                releaseDate = extractYear(item.releaseDate)
            )
        }


    private fun extractYear(stringDate: String?): Date? =
        stringDate?.let {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
            dateFormat.parse(stringDate)
        }

}