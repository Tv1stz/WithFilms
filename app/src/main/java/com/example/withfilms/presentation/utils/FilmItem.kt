package com.example.withfilms.presentation.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.withfilms.domain.model.Film
import com.example.withfilms.presentation.ui.theme.RatingGreen

@Composable
fun FilmItem(
    film: Film,
    onFilmClick: (filmId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .width(155.dp)
            .height(250.dp),
        contentAlignment = Alignment.Center,
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
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .height(210.dp),
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