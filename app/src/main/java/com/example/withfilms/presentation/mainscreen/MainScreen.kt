package com.example.withfilms.presentation.mainscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.AsyncImage
import com.example.withfilms.R
import com.example.withfilms.domain.model.Film
import com.example.withfilms.presentation.ui.theme.RatingGreen
import com.example.withfilms.presentation.ui.theme.SearchMenuColor
import com.example.withfilms.presentation.ui.theme.WithFilmsTheme
import com.example.withfilms.presentation.utils.ErrorScreen
import com.example.withfilms.presentation.utils.LoadingScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onFilmClick: (Int) -> Unit,
    onSearchClick: () -> Unit,
    viewModel: MainViewModel = hiltViewModel(),
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val films = state.value.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            MainTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, top = 8.dp)
            )
        }
    ) {
        Box(modifier = Modifier.padding(it)) {
            Column {
                SearchCard(
                    onSearchClick = onSearchClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                )
                when (films.loadState.refresh) {
                    is LoadState.Loading -> {
                        LoadingScreen(
                            modifier = Modifier.fillMaxSize()
                        )
                    }

                    is LoadState.Error -> {
                        ErrorScreen(
                            modifier = Modifier.fillMaxSize(),
                            onRefreshClick = { films.refresh() }
                        )
                    }

                    is LoadState.NotLoading -> {
                        FilmsView(
                            films = films,
                            onFilmClick = onFilmClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FilmsView(
    films: LazyPagingItems<Film>,
    onFilmClick: (Int) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(top = 10.dp, bottom = 10.dp, start = 10.dp, end = 10.dp)
    ) {
        items(
            count = films.itemCount,
            key = films.itemKey(),
            contentType = films.itemContentType { }
        ) { index ->
            val film = films[index]
            film?.let {
                FilmCard(
                    film = film,
                    onFilmClick = onFilmClick,
                    modifier = Modifier.padding(horizontal = 15.dp, vertical = 6.dp)
                )
            }
        }

        item(span = { GridItemSpan(2) }) {
            when (films.loadState.append) {
                is LoadState.Loading -> {
                    LoadingScreen(
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                is LoadState.Error -> {
                    ErrorScreen(
                        modifier = Modifier.fillMaxWidth(),
                        onRefreshClick = { films.retry() }
                    )
                }

                is LoadState.NotLoading -> {}
            }
        }

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmCard(
    film: Film,
    onFilmClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(145.dp),
        onClick = { onFilmClick(film.filmId) }
    ) {
        Box(modifier = Modifier) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                AsyncImage(
                    model = film.posterUrl,
                    contentDescription = film.nameRu,
                    modifier = Modifier
                        .height(210.dp)
                        .clip(RoundedCornerShape(16.dp))
                )
                Text(
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                    text = film.nameRu,
                    maxLines = 1,
                    style = MaterialTheme.typography.titleSmall,
                    overflow = TextOverflow.Ellipsis
                )
            }
            RatingView(
                rating = film.rating,
                modifier = Modifier.padding(top = 10.dp)
            )
        }
    }
}

@Composable
fun RatingView(
    rating: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(3.dp))
            .background(RatingGreen)
            .width(23.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = rating,
            style = MaterialTheme.typography.labelMedium,
            color = Color.White
        )
    }
}

@Composable
fun SearchCard(
    modifier: Modifier = Modifier,
    onSearchClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .clickable { onSearchClick() },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.padding(8.dp),
                painter = painterResource(id = R.drawable.unactive_search),
                contentDescription = stringResource(id = R.string.search_films)
            )
            Text(
                text = stringResource(id = R.string.search_films),
                style = MaterialTheme.typography.titleSmall,
                color = SearchMenuColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchCardPreview() {
    WithFilmsTheme {
        SearchCard(
            modifier = Modifier.fillMaxWidth(),
            onSearchClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilmCardPreview() {
    WithFilmsTheme {
        FilmCard(
            film = Film(
                filmId = 1,
                nameRu = "Мстители: Конец",
                posterUrl = "",
                rating = "9.1"
            ),
            onFilmClick = {}
        )
    }
}

@Preview
@Composable
fun RatingViewPreview() {
    WithFilmsTheme {
        RatingView(rating = "9.1")
    }
}