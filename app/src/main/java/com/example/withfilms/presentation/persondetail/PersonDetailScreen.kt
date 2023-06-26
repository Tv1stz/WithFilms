package com.example.withfilms.presentation.persondetail

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.withfilms.R
import com.example.withfilms.domain.model.PersonDetail
import com.example.withfilms.domain.model.PersonFilm
import com.example.withfilms.presentation.utils.CustomTopAppBar
import com.example.withfilms.presentation.utils.ErrorScreen
import com.example.withfilms.presentation.utils.LoadState.*
import com.example.withfilms.presentation.utils.LoadingScreen
import com.example.withfilms.presentation.utils.RatingItem

@Composable
fun PersonDetailScreen(
    onFilmClick: (filmId: Int) -> Unit,
    onBackClick: () -> Unit,
    personId: Int,
    viewModel: PersonDetailViewModel = hiltViewModel()
) {
    LaunchedEffect(true) {
        viewModel.getPersonDetail(personId)
    }

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    when (uiState.loadState) {
        SUCCESS -> {
            uiState.personDetail?.let {
                PersonDetail(
                    personDetail = it,
                    onFilmClick = onFilmClick,
                    onBackClick = onBackClick
                )
            }
        }

        ERROR -> {
            ErrorScreen(
                modifier = Modifier.fillMaxSize(),
                message = stringResource(id = R.string.something_went_wrong),
            )
        }

        LOADING -> {
            LoadingScreen(
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PersonDetail(
    personDetail: PersonDetail,
    onFilmClick: (filmId: Int) -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CustomTopAppBar(title = personDetail.nameRu) {
                onBackClick()
            }
        }) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            Column(
                modifier = Modifier.padding(top = 10.dp)
            ) {
                PersonInfo(personDetail = personDetail)
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = stringResource(id = R.string.films),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(start = 29.dp, bottom = 8.dp)
                )
                LazyColumn {
                    items(items = personDetail.films) { film ->
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
fun PersonInfo(
    personDetail: PersonDetail
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 28.dp),
    ) {
        AsyncImage(
            model = personDetail.posterUrl,
            contentDescription = personDetail.nameRu,
            modifier = Modifier
                .height(226.dp)
                .width(144.dp)
                .clip(RoundedCornerShape(16.dp)),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = personDetail.nameRu,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = personDetail.nameEn,
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = personDetail.profession,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            InformationOnPerson(
                info = "${stringResource(id = R.string.birthday)} ${personDetail.birthday}"
            )
            InformationOnPerson(
                info = "${stringResource(id = R.string.death)} ${personDetail.death}"
            )
            InformationOnPerson(info = "${stringResource(id = R.string.age)} ${personDetail.age}")
            InformationOnPerson(info = "${stringResource(id = R.string.sex)} ${personDetail.sex}")
        }
    }
}

@Composable
fun InformationOnPerson(
    info: String
) {
    Text(
        text = info,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier.padding(bottom = 4.dp)
    )
}

@Composable
fun FilmCard(
    film: PersonFilm,
    onFilmClick: (filmId: Int) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onFilmClick(film.filmId) }
    ) {
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
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp)
            )
        }
    }
}

