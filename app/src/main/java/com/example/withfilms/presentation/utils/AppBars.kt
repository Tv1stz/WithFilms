package com.example.withfilms.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.withfilms.R

@Composable
fun CustomBottomAppBar(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        ButtonForBottomAppBar(img = R.drawable.unactive_home, text = "Home")
        ButtonForBottomAppBar(img = R.drawable.active_search, text = "Search")
    }
}

@Composable
fun ButtonForBottomAppBar(
    img: Int,
    text: String
) {
    Column(
        modifier = Modifier.padding(5.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = img),
            contentDescription = text
        )
        Text(
            text = text,
            fontSize = 11.sp
        )
    }
}

@Composable
fun CustomTopAppBar(
    title: String,
    onBackClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.arrow_back),
            contentDescription = "back",
            modifier = Modifier
                .clickable { onBackClick() }
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            modifier = Modifier.weight(1f).padding(end = 40.dp),
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CustomTopAppBarPreview() {
    CustomTopAppBar(
        title = "Описание",
        onBackClick = {}
    )
}