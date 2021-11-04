package com.lfgtavora.trendingmovies.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lfgtavora.feed.presentation.viewmodel.FeedViewModel
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
            HomeScreen(
                navHostController = navController,
                feedViewModel = getViewModel<FeedViewModel>()
            )
        }
        composable(Route.DETAILS.label + "/{id}") {
            it.arguments?.getString("id")?.let { id ->
                val viewmodel = getViewModel<MovieDetailViewModel>()
                DetailScreen(
                    id = id,
                    viewmodel= viewmodel,
                    navHostController = navController,
                    movieDetailState = viewmodel.movieDetailState
                )
            }
        }
    }
}