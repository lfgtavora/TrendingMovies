package com.lfgtavora.feed.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.lfgtavora.designsystem.utils.*
import com.lfgtavora.feed.R
import com.lfgtavora.feed.domain.model.MovieDomain
import com.lfgtavora.feed.presentation.viewmodel.FeedViewModel
import com.lfgtavora.feed.presentation.viewmodel.UiState

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun FeedView(viewModel: FeedViewModel, onItemClick: (Int) -> Unit) {
    val popularMoviesState = viewModel.popularMoviesState.value
    val lazyListState = rememberLazyListState()

    LazyColumn(state = lazyListState) {
        item {
            FeedTitle(stringResource(R.string.feed_title_lancamentos))
        }
        item {
            FeedCarrousel(viewModel, onItemClick)
        }
        item {
            FeedTitle(stringResource(R.string.feed_title_polular))
            if (popularMoviesState.isLoading || popularMoviesState.error != null)
                RetryCard(isLoading = popularMoviesState.isLoading) {
                    viewModel.getPopularMovies()
                }
        }
        if (popularMoviesState.items.isNotEmpty()) {
            itemsIndexed(popularMoviesState.items) { index, movie ->
                FeedItem(movie, index + 1, onItemClick)
            }
            item {
                VerticalSpacer.Medium()
                Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
                    TextButton(onClick = { }) {
                        Text(text = "Ver tudo", style = MaterialTheme.typography.button)
                    }
                }
                VerticalSpacer.Medium()
            }
        }

    }


}

@Composable
fun FeedTitle(title: String) {
    VerticalSpacer.Medium()
    Text(
        text = title,
        style = MaterialTheme.typography.h6,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
    VerticalSpacer.Medium()
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun FeedCarrousel(viewModel: FeedViewModel, onItemClick: (Int) -> Unit) {
    if (viewModel.recentsMoviesState.value.isLoading.not()) {
        val pagerState = rememberPagerState()
        HorizontalPager(
            state = pagerState,
            count = viewModel.recentsMoviesState.value.items.size,
            contentPadding = PaddingValues(horizontal = 32.dp),
            itemSpacing = 16.dp,
            modifier = Modifier.height(180.dp)
        ) { page ->
            FeedCarrouselItem(viewModel.recentsMoviesState.value.items[page], onItemClick)
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun FeedCarrouselItem(movie: MovieDomain, onItemClick: (Int) -> Unit) {
    Card(
        elevation = 16.dp,
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape = RoundedCornerShape(10.dp)),
        onClick = { onItemClick(movie.id) }
    ) {
        Image(
            painter = rememberImagePainter("https://www.themoviedb.org/t/p/w500/${movie.cover}"),
            contentDescription = null,
            modifier = Modifier
                .background(Color.LightGray)
                .fillMaxHeight(),
            contentScale = ContentScale.Crop

        )
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.5f)),
                        start = Offset(0f, 0f),
                        end = Offset(0f, Float.POSITIVE_INFINITY)
                    )
                )
                .padding(16.dp)
                .fillMaxSize()
        ) {

            Text(
                text = movie.title ?: "",
                style = MaterialTheme.typography.h6,
                color = Color.White
            )
            VerticalSpacer.Xsmall()
            Row {
                Ratings(4.0)
                HorizontalSpacer.Small()
                Text(
                    text = movie.releaseDate?.time.toString(),
                    style = MaterialTheme.typography.caption,
                    color = Color.White
                )
            }

        }
    }

}

@ExperimentalMaterialApi
@Composable
fun FeedItem(movie: MovieDomain, index: Int, onItemClick: (Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp)
            .fillMaxWidth(),
        elevation = 0.dp,
        backgroundColor = Color.Transparent,
        onClick = {
            onItemClick(movie.id)
        }
    ) {
        Row(
            Modifier
                .height(90.dp)
                .padding(8.dp)
        ) {
            Text(text = "$index.", Modifier.width(28.dp), style = MaterialTheme.typography.caption)
            Image(
                painter = rememberImagePainter("https://image.tmdb.org/t/p/w200/${movie.poster}"),
                contentDescription = "Poster do filme",
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(6.dp))
                    .width(50.dp)
                    .background(color = MaterialTheme.colors.onBackground.copy(alpha = 0.1f)),
                contentScale = ContentScale.Fit
            )

            Column(modifier = Modifier.padding(horizontal = 8.dp)) {
                Text(text = movie.title ?: "", style = MaterialTheme.typography.subtitle1)
                VerticalSpacer.Xsmall()
                Ratings(movie.rating)
            }
        }
    }

}

@Composable
fun Ratings(rating: Double) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.alpha(0.6f)) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            modifier = Modifier.size(12.dp),
        )
        HorizontalSpacer.Xsmall()
        Text(text = rating.toString(), style = MaterialTheme.typography.caption)
    }
}

@Composable
fun RetryCard(isLoading: Boolean, onClickReload: () -> Unit) {
    Card(
        elevation = 0.dp,
        modifier = Modifier
            .padding(VERTICAL_MEDIUM_SPACE.dp)
            .fillMaxWidth()
            .defaultMinSize(minHeight = 100.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator()
            } else {
                Text(text = "Algo deu errado. Tente novamente.")
                VerticalSpacer.Small()
                TextButton(onClick = { onClickReload() }) {
                    Text(text = "Tentar novamente", style = MaterialTheme.typography.button)
                }
            }
        }
    }
}

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun FeedViewPreview() {

}

@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun FeedCarrouselItemPreview() {
}

@Preview(showBackground = true)
@Composable
fun RetryCardPreview() {
    RetryCard(onClickReload = { /*TODO*/ }, isLoading = true)
}