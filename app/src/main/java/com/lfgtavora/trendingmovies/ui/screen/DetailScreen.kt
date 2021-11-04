package com.lfgtavora.trendingmovies.ui.screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lfgtavora.movie_detail.presentation.ui.MovieDetailView
import com.lfgtavora.movie_detail.presentation.viewmodel.UiState

@ExperimentalMaterialApi
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    movieDetailState: UiState,
    onScreenLoad: () -> Unit
) {
    Scaffold(
        topBar = {
            TopBar(movieDetailState, onScreenLoad) {
                navHostController.popBackStack()
            }
        }
    ) {}
}

@Composable
private fun TopBar(
    movieDetailState: UiState,
    onScreenLoad: () -> Unit,
    onNavigateBack: () -> Unit
) {
    MovieDetailView(movieDetailState, onScreenLoad)
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