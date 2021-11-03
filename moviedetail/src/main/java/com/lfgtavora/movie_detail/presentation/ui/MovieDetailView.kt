package com.lfgtavora.movie_detail.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.flowlayout.FlowRow
import com.lfgtavora.designsystem.utils.*
import com.lfgtavora.movie_detail.domain.model.MovieDetailDomain
import com.lfgtavora.movie_detail.presentation.viewmodel.MovieDetailViewModel

@Composable
fun MovieDetailView(id: String, viewModel: MovieDetailViewModel) {
    val movieDetailState by remember {
        viewModel.movieDetailState
    }

    if (movieDetailState.movie == null || movieDetailState.movie?.id != id)
        viewModel.getMovieDetail(id)

    if (movieDetailState.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    } else {
        movieDetailState.movie?.let { movie ->
            Column(Modifier.verticalScroll(rememberScrollState())) {
                Cover(movie.cover)
                Column(Modifier.padding(horizontal = 16.dp)) {
                    VerticalSpacer.Medium()
                    Metadata(movie)
                    VerticalSpacer.Medium()
                    Title(movie)
                    VerticalSpacer.Medium()
                    Description(value = movie.description)
                }
            }
        }
    }

}

@Composable
private fun Cover(cover: String) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(460.dp)
    ) {
        Image(
            painter = rememberImagePainter("https://image.tmdb.org/t/p/w1920_and_h800_multi_faces/$cover"),
            contentDescription = "Poster do filme",
            modifier = Modifier
                .height(460.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Transparent, MaterialTheme.colors.background),
                        start = Offset(0f, 0f),
                        end = Offset(0f, Float.POSITIVE_INFINITY)
                    )
                )
                .fillMaxSize()
        )
    }
}

@Composable
private fun Metadata(movie: MovieDetailDomain) {
    FlowRow(
        crossAxisSpacing = VERTICAL_SMALL_SPACE.dp,
        mainAxisSpacing = VERTICAL_SMALL_SPACE.dp
    ) {
        if (movie.adult) Chip(value = "+18")
        movie.genres.forEach { genre -> Chip(value = genre.name) }
        Chip(icon = Icons.Filled.Star, value = movie.rating.toString())
    }
}

@Composable
private fun Chip(icon: ImageVector? = null, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(MaterialTheme.colors.onBackground.copy(alpha = 0.1f))
            .padding(vertical = 4.dp, horizontal = 8.dp)

    ) {
        icon?.let { icon ->
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = Color.Yellow,
                modifier = Modifier.size(12.dp)
            )
            HorizontalSpacer.Xsmall()
        }
        Text(
            text = value,
            style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.SemiBold),
            color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f)
        )
    }
}

@Composable
private fun Title(movie: MovieDetailDomain) {
    Column {
        Text(
            text = movie.title,
            style = MaterialTheme.typography.h6,
            color = MaterialTheme.colors.onBackground
        )
        Text(
            text = movie.originalTitle,
            style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onBackground
        )
    }
}

@Composable
private fun Companies() {

}

@Composable
private fun Description(value: String) {
    Text(
        text = value,
        style = MaterialTheme.typography.body2,
        color = MaterialTheme.colors.onBackground.copy(alpha = 0.7f)
    )
}