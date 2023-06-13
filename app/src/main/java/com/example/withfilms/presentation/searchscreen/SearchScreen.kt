package com.example.withfilms.presentation.searchscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.withfilms.presentation.utils.CustomSearch
import com.example.withfilms.presentation.utils.FilmItem

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onFilmClick: (filmId: Int) -> Unit,
) {

    val state by viewModel.searchResult.collectAsStateWithLifecycle()

    Box(modifier = modifier.padding(top = 10.dp)) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CustomSearch(
                query = state.query,
                onChangeQuery = {
                    viewModel.onNewQuery(it)
                },
                hint = "search"
            )
            if (state.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) { CircularProgressIndicator() }
            } else {
                LazyVerticalGrid(columns = GridCells.Fixed(2),) {
                    items(items = state.films, key = {it.filmId}) { film ->
                        FilmItem(
                            film = film,
                            onFilmClick = onFilmClick
                        )
                    }
                }
            }
        }
    }
    // TODO("Фильмы которые скоро выйдут, жанры")
}



