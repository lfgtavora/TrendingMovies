package com.lfgtavora.trendingmovies.ui.screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lfgtavora.movie_detail.presentation.ui.MovieDetailView
import com.lfgtavora.movie_detail.presentation.viewmodel.MovieDetailViewModel
import com.lfgtavora.movie_detail.presentation.viewmodel.UiState

@ExperimentalMaterialApi
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    movieDetailState: State<UiState>,
    viewmodel: MovieDetailViewModel,
    id: String
) {
    viewmodel.getMovieDetail(id)
    Scaffold(
        topBar = {
            TopBar(movieDetailState) {
                navHostController.popBackStack()
            }
        }
    ) {}
}

@Composable
private fun TopBar(movieDetailState: State<UiState>, onNavigateBack: () -> Unit) {
    MovieDetailView(movieDetailState)
    TopAppBar(
        title = { Text(text = "") },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = { onNavigateBack() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = "Voltar a tela anterior"
                )
            }
        },
    )

}