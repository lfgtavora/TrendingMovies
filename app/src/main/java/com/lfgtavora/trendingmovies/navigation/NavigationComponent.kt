package com.lfgtavora.trendingmovies.navigation

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.SideEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.lfgtavora.feed.presentation.viewmodel.FeedViewModel
import com.lfgtavora.movie_detail.presentation.viewmodel.MovieDetailViewModel
import com.lfgtavora.trendingmovies.ui.screen.DetailScreen
import com.lfgtavora.trendingmovies.ui.screen.HomeScreen

@ExperimentalMaterialApi
@Composable
fun NavigationComponent(
    navController: NavHostController,
    feedViewModel: FeedViewModel,
    movieDetailViewModel: MovieDetailViewModel,
) {
    NavHost(
        navController = navController,
        startDestination = Route.HOME.label
    ) {

        composable(Route.HOME.label) {
            HomeScreen(navHostController = navController, feedViewModel = feedViewModel)
        }
        composable(Route.DETAILS.label + "/{id}") {
            it.arguments?.getString("id")?.let { id ->
                DetailScreen(
                    id = id,
                    navHostController = navController,
                    viewmodel = movieDetailViewModel
                )
            }
        }
    }
}