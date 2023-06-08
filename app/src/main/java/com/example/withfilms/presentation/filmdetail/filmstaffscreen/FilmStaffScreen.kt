package com.example.withfilms.presentation.filmdetail.filmstaffscreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.withfilms.domain.model.Staff
import com.example.withfilms.presentation.utils.CustomTopAppBar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FilmStaffScreen(
    staff: List<Staff>,
    filmName: String,
    onBackClick: () -> Unit,
    viewModel: FilmStaffViewModel = hiltViewModel()
) {

    LaunchedEffect(true) {
        viewModel.sortedStaff(staff)
    }

    val staffState by viewModel.staffUiState.collectAsStateWithLifecycle()
    val localStaff = staffState.staff

    Scaffold(
        topBar = {
            CustomTopAppBar(
                title = filmName,
                onBackClick = { onBackClick() }
            )
        }
    ) { contentPadding ->
        Box(modifier = Modifier.padding(contentPadding)) {
            if (staffState.isLoading) {
                CircularProgressIndicator()
            } else {
                LazyColumn {
                    localStaff.forEach { (key, value) ->
                        stickyHeader {
                            SubItems(subItems = key)
                        }

                        items(value) {actor ->
                            ActorItem(actor = actor)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SubItems(
    subItems: String,
) {
    Row {
       Text(
           text = subItems,
           style = MaterialTheme.typography.titleMedium
       ) 
    }
}

@Composable
fun ActorItem(
    actor: Staff
) {
    Row(
        modifier = Modifier
            .height(100.dp)
            .padding(4.dp)
    ) {
        AsyncImage(
            model = actor.posterUrl,
            contentDescription = actor.nameRu,
            modifier = Modifier.width(64.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp)
        ) {
            Text(
                text = actor.nameRu,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 4.dp)
            )
            Text(
                text = actor.nameEn,
                style = MaterialTheme.typography.bodySmall
            )
            Text(
                text = actor.description ?: "",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                modifier = Modifier.padding(top = 24.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ActorItemPreview() {
    ActorItem(
        actor = Staff(
            description = "Clara Mayfield",
            nameEn = "Whoopi Goldberg",
            nameRu = "Вупи Голдберг",
            posterUrl = "",
            professionKey = "ACTOR",
            professionText = "Актеры",
            staffId = 212
        )
    )
}