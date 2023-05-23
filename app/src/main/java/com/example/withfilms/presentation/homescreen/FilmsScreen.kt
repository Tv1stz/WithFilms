package com.example.withfilms.presentation.homescreen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.withfilms.data.remote.model.Film
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.withfilms.presentation.ui.theme.RatingGreen
import com.example.withfilms.presentation.ui.theme.WithFilmsTheme
import com.example.withfilms.presentation.utils.CustomBottomAppBar
import com.example.withfilms.presentation.utils.CustomTextField

@ExperimentalMaterial3Api
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: FilmsViewModel = hiltViewModel()
) {
    val films =
        viewModel.getFilm().collectAsLazyPagingItems()
    Scaffold(
        bottomBar = { CustomBottomAppBar() }
    ) {contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column {
                Text(
                    text = "With Films",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(start = 19.dp, top = 5.dp, bottom = 9.dp)
                )
                CustomTextField(
                    userRequest = viewModel.search,
                    onUserRequestChanged = { viewModel.searchFilms(it) }
                )
                Spacer(modifier = Modifier.height(10.dp))
                FilmsList(films = films)
            }
        }
    }

}

@Composable
fun FilmsList(
    films: LazyPagingItems<Film>
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
    ) {
        items(
            count = films.itemCount,
            key = films.itemKey(),
            contentType = films.itemContentType()
        ) { index ->
            val film = films[index]
            film?.let {
                FilmItem(
                    film = film,
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
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Surface(
            modifier = Modifier
                .width(145.dp)
                .height(250.dp),
        ) {
            Box(modifier = Modifier.width(145.dp)) {
                Column(
                    modifier = Modifier
                        .width(140.dp)
                        .align(Alignment.TopEnd)
                        .clickable {  },
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
    rating: String,
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
            text = rating,
            fontSize = 10.sp,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
fun FilmItemPreview() {
    WithFilmsTheme {
        FilmItem(
            film = Film(
                countries = emptyList(),
                filmId = 121,
                filmLength = "",
                genres = emptyList(),
                nameEn = "Avengers: Final",
                nameRu = "Avengers: Final",
                posterUrl = "",
                posterUrlPreview = "",
                rating = "9.1",
                ratingChange = "",
                ratingVoteCount = 1,
                year = "2222"
            )
        )
    }
}






