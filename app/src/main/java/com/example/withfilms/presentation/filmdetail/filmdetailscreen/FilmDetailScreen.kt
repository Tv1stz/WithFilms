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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.withfilms.R
import com.example.withfilms.domain.model.FilmStaff
import com.example.withfilms.domain.model.Genre
import com.example.withfilms.presentation.filmdetail.FilmDetailUiState
import com.example.withfilms.presentation.ui.theme.WithFilmsTheme
import com.example.withfilms.presentation.utils.RatingItem
import com.example.withfilms.presentation.utils.ShowMoreButton

@Composable
fun FilmDetailScreen(
    detail: FilmDetailUiState,
    onBackClick: () -> Unit,
    onDescriptionClick: () -> Unit,
    onShowMoreStaffClick: () -> Unit,
    onActorDetail: (actorId: Int) -> Unit,
) {
    FilmDetailItem(
        filmDescription = detail.filmDetail!!.description,
        filmName = detail.filmDetail.name,
        rating = detail.filmDetail.rating,
        year = detail.filmDetail.year,
        filmLength = detail.filmDetail.filmLength,
        posterPreview = detail.filmDetail.posterUrlPreview,
        poster = detail.filmDetail.posterUrl,
        genres = detail.filmDetail.genre,
        persons = detail.filmStaff,
        onDescriptionClick = onDescriptionClick,
        onShowMoreStaffClick = onShowMoreStaffClick,
        onActorDetail = onActorDetail,
        onBackClick = onBackClick,
    )
}


@Composable
fun FilmDetailItem(
    filmDescription: String,
    filmName: String,
    rating: String,
    year: String,
    filmLength: String,
    posterPreview: String,
    poster: String,
    genres: List<Genre>,
    onBackClick: () -> Unit = {},
    persons: List<FilmStaff>,
    onDescriptionClick: () -> Unit,
    onShowMoreStaffClick: () -> Unit,
    onActorDetail: (actorId: Int) -> Unit
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
                contentDescription = stringResource(id = R.string.back),
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
            persons = persons,
            onShowMoreStaffClick = onShowMoreStaffClick,
            onActorDetail = onActorDetail
        )
    }

}

@Composable
fun FilmData(
    modifier: Modifier = Modifier,
    rating: String,
    filmName: String,
    year: String,
    filmLength: String,
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
            text = stringResource(id = R.string.description),
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
                    text = stringResource(id = R.string.read_more),
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
    persons: List<FilmStaff>,
    onShowMoreStaffClick: () -> Unit,
    onActorDetail: (actorId: Int) -> Unit
) {
    Column {
        Text(
            text = stringResource(id = R.string.creators),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(start = 16.dp, top = 15.dp, bottom = 8.dp)
        )

        val actorsSubList = if (persons.size > 14) {
            persons.subList(0, 15)
        } else {
            persons
        }
        LazyRow {
            items(
                items = actorsSubList,
            ) {
                ActorsItem(
                    actorPoster = it.posterUrl,
                    actorName = it.nameRu,
                    actorId = it.staffId,
                    onActorDetail = onActorDetail
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
    actorId: Int,
    actorPoster: String,
    actorName: String,
    onActorDetail: (actorId: Int) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(horizontal = 5.dp)
            .width(86.dp)
            .clickable { onActorDetail(actorId) }
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