package com.example.withfilms.presentation.filmsscreen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.withfilms.domain.model.Film
import com.example.withfilms.presentation.ui.theme.RatingGreen
import com.example.withfilms.presentation.utils.CustomBottomAppBar
import com.example.withfilms.presentation.utils.CustomTextField

@ExperimentalMaterial3Api
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmsScreen(
    viewModel: FilmsViewModel = hiltViewModel(),
    onFilmClick: (filmId: Int) -> Unit,
) {

    val state = viewModel.filmUiState.collectAsStateWithLifecycle()
    val films = state.value.collectAsLazyPagingItems()

    Scaffold(
        bottomBar = { CustomBottomAppBar() }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column {
                Text(
                    text = "With Films",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(start = 19.dp, top = 5.dp, bottom = 9.dp)
                )
                CustomTextField(
                    userRequest = viewModel.search,
                    onUserRequestChanged = { viewModel.searchFilms(it) }
                )
                Spacer(modifier = Modifier.height(10.dp))
                FilmsList(films = films, onFilmClick = onFilmClick)
            }
        }
    }
}

@Composable
fun FilmsList(
    films: LazyPagingItems<Film>,
    onFilmClick: (filmId: Int) -> Unit,
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
    ) {
        items(
            count = films.itemCount,
            key = films.itemKey(),
            contentType = films.itemContentType { "contentType" }
        ) { index ->
            val film = films[index]
            film?.let {
                FilmItem(
                    film = it,
                    onFilmClick = onFilmClick,
                    Modifier
                        .padding(10.dp),
                )
            }

        }
    }
}

@Composable
fun FilmItem(
    film: Film,
    onFilmClick: (filmId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            modifier = Modifier
                .width(155.dp)
                .height(250.dp),
        ) {
            Box(modifier = Modifier.width(155.dp)) {
                Column(
                    modifier = Modifier
                        .width(150.dp)
                        .align(Alignment.TopEnd)
                        .clickable { onFilmClick(film.filmId) },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    AsyncImage(
                        modifier = Modifier.clip(RoundedCornerShape(16.dp)),
                        model = film.posterUrl,
                        contentDescription = film.nameRu
                    )
                    Text(
                        text = film.nameRu,
                        style = MaterialTheme.typography.titleSmall,
                        modifier = Modifier.padding(top = 5.dp),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                RatingView(
                    rating = film.rating,
                    Modifier.padding(top = 5.dp)
                )
            }
        }
    }
}

@Composable
fun RatingView(
    rating: String?,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(RatingGreen)
            .height(14.dp)
            .width(23.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = rating ?: "0.0",
            fontSize = 10.sp,
            style = MaterialTheme.typography.titleMedium
        )
    }
}







