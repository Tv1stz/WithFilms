package com.example.withfilms.presentation.searchscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.withfilms.R
import com.example.withfilms.presentation.ui.theme.WithFilmsTheme
import com.example.withfilms.presentation.utils.CustomSearch
import com.example.withfilms.presentation.utils.FilmItem
import com.example.withfilms.presentation.utils.LoadState
import com.example.withfilms.presentation.utils.LoadingScreen
import com.example.withfilms.presentation.utils.NotFoundScreen

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
    onFilmClick: (filmId: Int) -> Unit,
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Box(modifier = modifier.padding(top = 10.dp)) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CustomSearch(
                query = uiState.query,
                onChangeQuery = {
                    viewModel.onNewQuery(it)
                },
                hint = stringResource(id = R.string.search_films)
            )

            when(uiState.loadState) {
                LoadState.LOADING -> {
                    LoadingScreen(
                        modifier = Modifier.fillMaxSize()
                    )
                }

                LoadState.ERROR -> {
                    NotFoundScreen(
                        modifier = Modifier.fillMaxSize(),
                        message = stringResource(id = R.string.nothing_found),
                    )
                }

                else -> {
                    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                        items(items = uiState.films, key = { it.filmId }) { film ->
                            FilmItem(
                                film = film,
                                onFilmClick = onFilmClick,
                                Modifier
                                    .padding(10.dp),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    WithFilmsTheme() {
        SearchScreen(
            modifier = Modifier.fillMaxSize(),
            onFilmClick = {}
        )
    }
}



