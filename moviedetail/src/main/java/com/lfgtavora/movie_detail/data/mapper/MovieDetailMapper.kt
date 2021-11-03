package com.lfgtavora.movie_detail.data.mapper

import com.lfgtavora.movie_detail.data.remote.model.MovieDetailResponse
import com.lfgtavora.movie_detail.domain.model.Company
import com.lfgtavora.movie_detail.domain.model.CustomDate
import com.lfgtavora.movie_detail.domain.model.Genre
import com.lfgtavora.movie_detail.domain.model.MovieDetailDomain
import com.lfgtavora.networking.mapper.BaseMapper

class MovieDetailMapper : BaseMapper.ToDomain<MovieDetailResponse, MovieDetailDomain> {
    override fun toDomain(entity: MovieDetailResponse): MovieDetailDomain =
        with(entity) {
            MovieDetailDomain(
                id = id.toString(),
                title = title,
                originalTitle = originalTitle,
                poster = posterPath,
                cover = backdropPath,
                rating = voteAverage,
                releaseDate = convertToCustomDate(releaseDate),
                homepage = homepage,
                adult = adult,
                companies = productionCompanies.map { company ->
                    with(company) {
                        Company(id, logoPath, name)
                    }
                },
                description = overview,
                genres = genres.map { genre -> Genre(genre.id, genre.name) }
            )
        }

    private fun convertToCustomDate(stringDate: String?): CustomDate? =
        stringDate?.let {
            val (year, month, day) = stringDate.split("-")
            CustomDate(year, month, day)
        }

}