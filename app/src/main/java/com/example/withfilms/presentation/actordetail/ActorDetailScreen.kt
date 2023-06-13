package com.example.withfilms.presentation.actordetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.withfilms.domain.model.ActorFilms
import com.example.withfilms.presentation.utils.CustomTopAppBar
import com.example.withfilms.presentation.utils.RatingItem


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActorDetailScreen(
    modifier: Modifier = Modifier,
    onFilmClick: (filmId: Int) -> Unit,
    onBackClick: () -> Unit,
    actorId: Int,
    viewModel: ActorDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.getActorDetail(actorId)
    }

    val actorDetail by viewModel.actorDetailUiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
        CustomTopAppBar(title = actorDetail.nameRu) {
            onBackClick()
        }
    }) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column {
                ActorInfo(actorDetail = actorDetail)
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "Фильмы",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 29.dp, bottom = 8.dp)
                )
                LazyColumn{
                    items(items = actorDetail.films) { film ->
                        FilmCard(
                            film = film,
                            onFilmClick = onFilmClick
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ActorInfo(
    actorDetail: ActorDetailUiState
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 28.dp),
    ) {
        AsyncImage(
            model = actorDetail.poster,
            contentDescription = actorDetail.nameRu,
            modifier = Modifier
                .height(226.dp)
                .width(144.dp)
                .clip(RoundedCornerShape(16.dp)),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = actorDetail.nameRu,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = actorDetail.nameEn,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text =  actorDetail.profession,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Дата рождения: ${actorDetail.birthday}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Дата смерти: ${actorDetail.death}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Возраст: ${actorDetail.age}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = "Пол: ${actorDetail.sex}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}

@Composable
fun FilmCard(
    film: ActorFilms,
    onFilmClick: (filmId: Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onFilmClick(film.filmId) }
    ){
        Column(
            modifier = Modifier.padding(horizontal = 22.dp)
        ) {
            Text(
                text = film.nameRu,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 2
            )
            Text(
                text = film.nameEn,
                style = MaterialTheme.typography.titleSmall,
                maxLines = 2
            )
            Spacer(modifier = Modifier.height(8.dp))
            RatingItem(rating = film.rating)
            Divider(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ActorDetailScreenPreview() {

}
