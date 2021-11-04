package com.lfgtavora.trendingmovies.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lfgtavora.feed.presentation.viewmodel.FeedViewModel
import com.lfgtavora.movie_detail.presentation.ui.MovieDetailView
import com.lfgtavora.movie_detail.presentation.viewmodel.MovieDetailViewModel
import com.lfgtavora.trendingmovies.ui.screen.DetailScreen
import com.lfgtavora.trendingmovies.ui.screen.HomeScreen
import org.koin.androidx.compose.getViewModel

@ExperimentalMaterialApi
@Composable
fun NavigationComponent(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = Route.HOME.label
    ) {

        composable(Route.HOME.label) {
            val feedViewModel = getViewModel<FeedViewModel>()

            HomeScreen(
                navHostController = navController,
                feedViewModel = getViewModel<FeedViewModel>()
            )
        }
        composable(Route.DETAILS.label + "/{id}") {
            val viewmodel = getViewModel<MovieDetailViewModel>()

            it.arguments?.getString("id")?.let { id ->
                DetailScreen(
                    navHostController = navController,
                    movieDetailState = viewmodel.movieDetailState.value,
                    onScreenLoad = {
                        viewmodel.getMovieDetail(id)
                    }
                )
            }

        }
    }

}