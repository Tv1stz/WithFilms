package com.example.withfilms.presentation.filmsscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.withfilms.domain.model.Film
import com.example.withfilms.presentation.utils.FilmItem



@ExperimentalMaterial3Api
@Composable
fun FilmsScreen(
    modifier: Modifier = Modifier,
    viewModel: FilmsViewModel = hiltViewModel(),
    onFilmClick: (filmId: Int) -> Unit,

) {

    val state = viewModel.filmUiState.collectAsStateWithLifecycle()
    val films = state.value.collectAsLazyPagingItems()
    Box(modifier = modifier) {
        Column {
            Text(
                text = "With Films",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(start = 14.dp, top = 10.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Топ 250 фильмов:",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(start = 24.dp, top = 4.dp)
            )
            Spacer(modifier = Modifier.height(4.dp))
            //  TODO("убрать search и добавить выбор TOP250, TOP100 etc.")
            FilmsPagingList(films = films, onFilmClick = onFilmClick)
        }
    }
}

@Composable
fun FilmsPagingList(
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







