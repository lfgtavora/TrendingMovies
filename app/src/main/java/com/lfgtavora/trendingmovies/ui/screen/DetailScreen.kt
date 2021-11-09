package com.lfgtavora.trendingmovies.ui.screen

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.lfgtavora.movie_detail.presentation.ui.MovieDetailState
import com.lfgtavora.movie_detail.presentation.ui.MovieDetailView
import com.lfgtavora.movie_detail.presentation.viewmodel.MovieDetailViewModel
import com.lfgtavora.movie_detail.presentation.viewmodel.UiState

@ExperimentalMaterialApi
@Composable
fun DetailScreen(
    navHostController: NavHostController,
    viewModel: MovieDetailViewModel,
    id: String
) {
    Scaffold(
        topBar = {
            MovieDetailState(id, viewModel)
            TopAppBar(
                title = { Text(text = "") },
                backgroundColor = Color.Transparent,
                elevation = 0.dp,
                navigationIcon = {
                    IconButton(onClick = { navHostController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            tint = MaterialTheme.colors.onBackground,
                            contentDescription = "Voltar a tela anterior"
                        )
                    }
                }
            )
        }
    ) {}
}
