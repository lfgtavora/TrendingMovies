package com.lfgtavora.movie_detail.core.di

import org.koin.core.Koin
import org.koin.core.component.KoinComponent

interface MovieDetailKoinComponent: KoinComponent {
    override fun getKoin(): Koin = MovieDetailModule.movieDetailKoin.koin
}