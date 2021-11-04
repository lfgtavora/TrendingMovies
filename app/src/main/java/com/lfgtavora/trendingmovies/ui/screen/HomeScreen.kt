package com.lfgtavora.trendingmovies.ui.screen

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.lfgtavora.designsystem.ThemeState
import com.lfgtavora.feed.presentation.ui.FeedView
import com.lfgtavora.feed.presentation.viewmodel.FeedViewModel
import com.lfgtavora.feed.presentation.viewmodel.UiState
import com.lfgtavora.trendingmovies.R
import com.lfgtavora.trendingmovies.navigation.Route

@ExperimentalMaterialApi
@Composable
fun HomeScreen(navHostController: NavHostController, feedViewModel: FeedViewModel) {
    Scaffold(
        floatingActionButton = { FabRandomMovie() },
        floatingActionButtonPosition = FabPosition.End,
        topBar = { TopBar(ThemeState.darkModeState) },
    ) {
        FeedView(
            viewModel = feedViewModel,
            onItemClick = { movieId ->
                navHostController.navigate(Route.DETAILS.label + "/" + movieId)
            }
        )
    }
}

@Composable
private fun FabRandomMovie() {
    FloatingActionButton(onClick = {}, backgroundColor = MaterialTheme.colors.primary) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_shuffle_24),
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Composable
private fun TopBar(isDark: Boolean) {
    TopAppBar(
        title = { Text(Route.HOME.title, color = Color.White) },
        actions = {
            IconButton(onClick = { toggleTheme(isDark) }) {
                Icon(
                    painter = painterResource(id = if (isDark) R.drawable.ic_baseline_light else R.drawable.ic_baseline_brightness_dark),
                    contentDescription = "",
                    tint = Color.White
                )
            }
        },
        backgroundColor = MaterialTheme.colors.primary
    )
}

private fun toggleTheme(isDark: Boolean) {
    val theme = when (isDark) {
        true -> AppCompatDelegate.MODE_NIGHT_NO
        false -> AppCompatDelegate.MODE_NIGHT_YES
    }
    AppCompatDelegate.setDefaultNightMode(theme)
    ThemeState.darkModeState = !isDark
}