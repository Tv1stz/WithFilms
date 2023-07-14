package com.example.withfilms.presentation.filmdetail

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import coil.compose.AsyncImage
import com.example.withfilms.R
import com.example.withfilms.domain.model.FilmDetail
import com.example.withfilms.domain.model.FilmStaff
import com.example.withfilms.presentation.ui.theme.WithFilmsTheme
import com.example.withfilms.presentation.ui.theme.blue
import com.example.withfilms.presentation.utils.PersonCard

@Composable
fun FilmDetailScreen(
    detail: DetailUiState,
    onBackClick: () -> Unit,
    onPersonClick: (Int) -> Unit,
    onShowMoreClick: () -> Unit,
) {
    LazyColumn {
        item {
            FilmDetailView(
                onBackClick = onBackClick,
                filmDetail = detail.filmDetail!!,
            )
        }
        item {
            PersonList(
                persons = detail.filmStaffList,
                onPersonClick = onPersonClick,
                onShowMoreClick = onShowMoreClick,
            )
        }
    }
}


@Composable
fun FilmDetailView(
    filmDetail: FilmDetail,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Box {
            AsyncImage(
                model = filmDetail.posterUrl,
                contentDescription = filmDetail.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))
                    .height(340.dp),
                alpha = 0.9f,
                contentScale = ContentScale.Crop
            )
            FilmDetail(
                modifier = Modifier
                    .padding(top = 283.dp)
                    .height(150.dp),
                rating = filmDetail.rating,
                filmName = filmDetail.name,
                year = filmDetail.year,
                filmLength = filmDetail.filmLength,
                posterPreview = filmDetail.posterUrlPreview
            )
            Image(painter = painterResource(id = R.drawable.arrow_back),
                contentDescription = stringResource(id = R.string.back),
                modifier = Modifier
                    .padding(start = 4.dp, top = 11.dp)
                    .clickable { onBackClick() })
        }
        Spacer(modifier = Modifier.height(13.dp))
        LazyRow(modifier = Modifier.padding(start = 8.dp)) {
            items(filmDetail.genre) {
                GenreItem(
                    genre = it.toString(), modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
        FilmDescription(filmDescription = filmDetail.description)
    }
}

@Composable
fun FilmDetail(
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
                RatingView(rating)

            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageWithText(img = R.drawable.calendar, text = year)
                Divider(
                    modifier = Modifier
                        .padding(horizontal = 26.dp)
                        .width(2.dp)
                        .height(37.dp)
                )
                ImageWithText(img = R.drawable.alarm, text = "$filmLength minute")
            }
        }
    }
}

@Composable
fun FilmDescription(
    filmDescription: String, modifier: Modifier = Modifier
) {
    var fullDescription by remember {
        mutableStateOf(false)
    }

    Column(modifier = modifier
        .padding(start = 16.dp, end = 16.dp)
        .animateContentSize(
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioNoBouncy, stiffness = Spring.StiffnessMedium
            )
        )
        .clickable { fullDescription = !fullDescription }) {
        Text(
            text = stringResource(id = R.string.description),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(top = 14.dp, start = 16.dp)
        )
        if (fullDescription) {
            Text(
                text = filmDescription,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 9.dp)
            )
            Text(
                text = stringResource(id = R.string.roll_up),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(start = 16.dp)
            )
        } else {
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

@Composable
fun GenreItem(
    genre: String, modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.clip(RoundedCornerShape(5.dp))
    ) {
        Text(
            text = genre,
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun ImageWithText(
    img: Int, text: String
) {
    Image(
        painter = painterResource(id = img), contentDescription = null
    )
    Text(
        modifier = Modifier.padding(start = 5.dp),
        text = text,
        style = MaterialTheme.typography.labelMedium,
        color = blue
    )
}

@Composable
fun PersonList(
    persons: List<FilmStaff>,
    onPersonClick: (Int) -> Unit,
    onShowMoreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(id = R.string.creators),
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
        LazyHorizontalGrid(
            rows = GridCells.Fixed(3),
            modifier = Modifier.height(316.dp)
        ) {
            items(persons.subList(0, 14)) { person ->
                PersonCard(
                    person = person,
                    onPersonClick = onPersonClick,
                    modifier = Modifier.padding(start = 8.dp, end = 8.dp, bottom = 4.dp)
                )
            }
            item(span = { GridItemSpan(3) }) {
                ShowMoreButton(
                    onShowMoreClick = onShowMoreClick,
                )
            }
        }
    }
}

@Composable
fun RatingView(
    rating: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.star), contentDescription = null
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = rating, style = MaterialTheme.typography.bodyLarge, color = Color(0xFFE2963B)
        )
    }
}

@Composable
fun ShowMoreButton(
    onShowMoreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.clickable { onShowMoreClick() }.padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.show_more_arrow),
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.show_more),
            style = MaterialTheme.typography.titleLarge
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RatingViewPreview() {
    WithFilmsTheme {
        RatingView(rating = "9.1")
    }
}

@Preview(showBackground = true)
@Composable
fun GenreItemPreview() {
    WithFilmsTheme {
        GenreItem(genre = "драмма")
    }
}

@Preview(showBackground = true)
@Composable
fun FilmDescriptionPreview() {
    WithFilmsTheme {
        FilmDescription(filmDescription = stringResource(id = R.string.description_preview))
    }
}


/*@Preview(showBackground = true)
@Composable
fun ShowMoreButtonPreview() {
    WithFilmsTheme {
        ShowMoreButton(
            onShowMoreClick = { String, String },
            persons = "",
            filmName = ""
        )
    }
}*/
