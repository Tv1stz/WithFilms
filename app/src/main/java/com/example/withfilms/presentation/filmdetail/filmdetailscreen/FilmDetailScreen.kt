package com.example.withfilms.presentation.filmdetail.filmdetailscreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.withfilms.R
import com.example.withfilms.domain.model.Staff
import com.example.withfilms.domain.model.Genre
import com.example.withfilms.presentation.filmdetail.FilmDetailUiState
import com.example.withfilms.presentation.ui.theme.WithFilmsTheme
import com.example.withfilms.presentation.utils.ShowMoreButton

@Composable
fun FilmDetailScreen(
    filmDetail: FilmDetailUiState,
    onBackClick: () -> Unit,
    onDescriptionClick: () -> Unit,
    onShowMoreStaffClick: () -> Unit
) {
    if (filmDetail.isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }

    } else {
        FilmDetailItem(
            filmDescription = filmDetail.description,
            filmName = filmDetail.filmName,
            rating = filmDetail.rating,
            year = filmDetail.year,
            filmLength = filmDetail.filmLength,
            posterPreview = filmDetail.posterPreview,
            poster = filmDetail.poster,
            genres = filmDetail.genres,
            onBackClick = onBackClick,
            actors = filmDetail.staff,
            onDescriptionClick = onDescriptionClick,
            onShowMoreStaffClick = onShowMoreStaffClick
        )
    }

}

@Composable
fun FilmDetailItem(
    filmDescription: String,
    filmName: String,
    rating: String,
    year: String,
    filmLength: Int,
    posterPreview: String,
    poster: String,
    genres: List<Genre>,
    onBackClick: () -> Unit = {},
    actors: List<Staff>,
    onDescriptionClick: () -> Unit,
    onShowMoreStaffClick: () -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        Box {
            AsyncImage(
                model = poster,
                contentDescription = filmName,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                    .height(340.dp),
                alpha = 0.9f,
                contentScale = ContentScale.Crop
            )
            FilmData(
                modifier = Modifier
                    .padding(top = 283.dp)
                    .height(150.dp),
                rating = rating,
                filmName = filmName,
                year = year,
                filmLength = filmLength,
                posterPreview = posterPreview
            )
            Image(
                painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = "back",
                modifier = Modifier
                    .padding(start = 4.dp, top = 11.dp)
                    .clickable { onBackClick() }
            )
        }
        Spacer(modifier = Modifier.height(13.dp))
        LazyRow(modifier = Modifier.padding(start = 8.dp)) {
            items(items = genres) {
                GenreItem(
                    genre = it.toString(),
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        FilmDescription(
            filmDescription = filmDescription,
            onDescriptionClick = onDescriptionClick
        )
        ActorsList(
            actors = actors,
            onShowMoreStaffClick = onShowMoreStaffClick
        )
    }

}

@Composable
fun FilmData(
    modifier: Modifier = Modifier,
    rating: String,
    filmName: String,
    year: String,
    filmLength: Int,
    posterPreview: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(start = 30.dp)
                .height(143.dp)
                .clip(RoundedCornerShape(16.dp)),
            model = posterPreview,
            contentDescription = filmName
        )
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 12.dp),
        ) {
            Row {
                Text(
                    modifier = Modifier.width(131.dp),
                    text = filmName,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 2,
                )
                Spacer(modifier = Modifier.width(12.dp))
                RatingItem(rating)

            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                FilmDataUtil(img = R.drawable.calendar, text = year)
                Divider(
                    modifier = Modifier
                        .padding(horizontal = 26.dp)
                        .width(2.dp)
                        .height(37.dp)
                )
                FilmDataUtil(img = R.drawable.alarm, text = "$filmLength minute")
            }
        }
    }
}


@Composable
fun GenreItem(
    genre: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = genre,
        fontSize = 12.sp,
        color = Color(0xFFACACAC),
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(Color(0xFF474747))
            .padding(vertical = 3.dp, horizontal = 3.dp)
    )
}

@Composable
fun FilmDescription(
    filmDescription: String,
    onDescriptionClick: () -> Unit,
) {
    Column {
        Text(
            text = "Описание:",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 14.dp, start = 16.dp)
        )
        Box(
            modifier = Modifier.clickable { onDescriptionClick() }
        ) {
            Column(
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 9.dp)
            ) {
                Text(
                    text = filmDescription,
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(
                    text = "Читать далее",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }

        }
    }
}

@Composable
fun ActorsList(
    actors: List<Staff>,
    onShowMoreStaffClick: () -> Unit

) {
    Column {
        Text(
            text = "Актеры",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 16.dp, top = 15.dp, bottom = 8.dp)
        )
        LazyRow {
            items(
                items = actors
                    .subList(0, 15)
                    .filter { it.professionKey == "ACTOR" },
                key = { it.staffId }
            ) {
                ActorsItem(
                    actorPoster = it.posterUrl,
                    actorName = it.nameRu
                )
            }
            item {
                ShowMoreButton(
                    onShowMoreClick = onShowMoreStaffClick
                )
            }
        }

    }
}

@Composable
fun ActorsItem(
    actorPoster: String,
    actorName: String,
    onActorDetail: (actorId: Long) -> Unit = {}
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .width(86.dp)
            .clickable { onActorDetail(2) }
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .height(110.dp),
            model = actorPoster,
            contentDescription = actorName
        )
        Text(
            text = actorName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleSmall
        )
    }
}

@Composable
fun RatingItem(
    rating: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.baseline_star_24),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = rating,
            color = Color(0xFFE2963B)
        )
    }
}

@Composable
fun FilmDataUtil(
    img: Int,
    text: String
) {
    Image(
        painter = painterResource(id = img),
        contentDescription = null
    )
    Text(
        modifier = Modifier.padding(start = 5.dp),
        text = text,
        fontSize = 12.sp,
        color = Color(0xFF92929D)
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun FilmDetailItemPreview() {
    WithFilmsTheme {
        Surface {
        }
    }
}