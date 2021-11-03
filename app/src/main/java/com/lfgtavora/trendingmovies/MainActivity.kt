package com.lfgtavora.trendingmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import com.lfgtavora.designsystem.ThemeState
import com.lfgtavora.designsystem.theme.TrendingMoviesTheme
import com.lfgtavora.feed.core.di.FeedModule
import com.lfgtavora.feed.presentation.viewmodel.FeedViewModel
import com.lfgtavora.movie_detail.core.di.MovieDetailModule
import com.lfgtavora.movie_detail.presentation.viewmodel.MovieDetailViewModel
import com.lfgtavora.trendingmovies.navigation.NavigationComponent
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.context.loadKoinModules

class MainActivity : ComponentActivity() {

    private lateinit var feedViewModel: FeedViewModel
    private lateinit var movieDetailViewModel: MovieDetailViewModel

    private val injectKoinModules by lazy {
        loadKoinModules(FeedModule.modules)
        loadKoinModules(MovieDetailModule.modules)
    }

    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injectKoinModules
        injectViewModules()

        setContent {
            val navController = rememberNavController()
            TrendingMoviesTheme(darkTheme = ThemeState.darkModeState) {
                Surface(color = MaterialTheme.colors.background) {
                    NavigationComponent(navController, feedViewModel, movieDetailViewModel)
                }
            }
        }
    }

    private fun injectViewModules() {
        feedViewModel = getViewModel<FeedViewModel>()
        movieDetailViewModel = getViewModel<MovieDetailViewModel>()
    }

}
