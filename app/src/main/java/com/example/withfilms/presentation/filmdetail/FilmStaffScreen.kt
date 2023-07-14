package com.example.withfilms.presentation.filmdetail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.withfilms.domain.model.FilmStaff
import com.example.withfilms.presentation.utils.PersonCard
import com.example.withfilms.presentation.utils.TopBar

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FilmStaffScreen(
    filmName: String,
    staff: Map<String, List<FilmStaff>>,
    onBackClick: () -> Unit,
    onPersonClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            TopBar(
                title = filmName,
                onBackClick = onBackClick
            )
        }
    ) { innerPadding ->
        Box(modifier = modifier.padding(innerPadding)) {
            LazyColumn {
                staff.forEach { (profession, persons) ->
                    stickyHeader {
                        ProfessionTitle(professionTitle = profession)
                    }
                    items(persons) { person ->
                        PersonCard(
                            person = person,
                            onPersonClick = onPersonClick,
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ProfessionTitle(
    professionTitle: String,
) {
    Row {
        Text(
            text = professionTitle,
            style = MaterialTheme.typography.titleMedium
        )
    }
}



