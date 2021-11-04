package com.lfgtavora.trendingmovies

import android.app.Application
import com.lfgtavora.feed.core.di.FeedModule
import com.lfgtavora.movie_detail.core.di.MovieDetailModule
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        FeedModule.init(this)
        MovieDetailModule.init(this)

        startKoin { loadKoinModules() }
    }

    private fun loadKoinModules() {
        loadKoinModules(FeedModule.modules)
        loadKoinModules(MovieDetailModule.modules)
    }
}