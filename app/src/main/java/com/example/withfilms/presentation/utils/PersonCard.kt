package com.example.withfilms.presentation.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.withfilms.domain.model.FilmStaff
import com.example.withfilms.presentation.ui.theme.WithFilmsTheme

@Composable
fun PersonCard(
    onPersonClick: (Int) -> Unit,
    person: FilmStaff,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .height(100.dp).width(230.dp)
            .clickable { onPersonClick(person.staffId) }
    ) {
        AsyncImage(
            model = person.posterUrl,
            contentDescription = person.nameRu,
            modifier = Modifier.clip(RoundedCornerShape(16.dp)).height(100.dp).width(64.dp),
            contentScale = ContentScale.FillBounds
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 8.dp)
        ) {
            Text(
                text = person.nameRu,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 4.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = person.nameEn,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = person.description,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(top = 24.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PersonCardPreview() {
    WithFilmsTheme {
        PersonCard(
            person = FilmStaff(
                description = "Clara Mayfield",
                nameEn = "Whoopi Goldberg",
                nameRu = "Вупи Голдберг",
                posterUrl = "",
                professionText = "Актеры",
                staffId = 212
            ),
            onPersonClick = {}
        )
    }
}