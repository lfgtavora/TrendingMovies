package com.lfgtavora.trendingmovies.ui.screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lfgtavora.movie_detail.presentation.ui.MovieDetailView
import com.lfgtavora.movie_detail.presentation.viewmodel.MovieDetailViewModel

@ExperimentalMaterialApi
@Composable
fun DetailScreen(
    id: String,
    navHostController: NavHostController,
    viewmodel: MovieDetailViewModel
) {
    Scaffold(
        topBar = { TopBar(id, viewmodel ,onNavigateBack = { navHostController.popBackStack() }) }
    ) {

    }
}

@Composable
private fun TopBar(id: String, viewmodel: MovieDetailViewModel, onNavigateBack: () -> Unit) {
    MovieDetailView(id, viewmodel)
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