package com.example.withfilms.presentation.screen


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.compose.AsyncImage
import com.example.withfilms.data.remote.model.Film
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.example.withfilms.presentation.ui.theme.WithFilmsTheme

@Composable
fun MainScreen(
    viewModel: MainViewModel = hiltViewModel()
) {
    val films =
        viewModel.getFilm().collectAsLazyPagingItems()
    SuccessScreen(films = films)

}

@Composable
fun SuccessScreen(
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
                FilmItem(film = film)
            }
        }
    }
}


@Composable
fun FilmItem(
    film: Film
) {
    Box(
        modifier = Modifier.height(230.dp).width(150.dp)
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier
                .height(210.dp)
                .width(140.dp)
                .align(Alignment.TopEnd)
        ) {
            AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
                model = film.posterUrl,
                contentDescription = film.nameRu,

            )
        }
        Text(
            modifier = Modifier.align(Alignment.BottomCenter),
            text = film.nameRu,
            fontSize = 14.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        RatingItem(
            rating = film.rating,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 5.dp, top = 5.dp)
        )
    }
    /*AsyncImage(model = film.posterUrlPreview, contentDescription = film.nameRu)*/
}


@Composable
fun RatingItem(
    rating: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(12.dp)
            .width(23.dp)
            .background(color = Color(red = 29, green = 81, blue = 0)),

        ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = rating,
            fontSize = 10.sp,
            color = Color.White,
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






