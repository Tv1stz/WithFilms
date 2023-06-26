package com.example.withfilms.presentation.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.withfilms.presentation.ui.theme.WithFilmsTheme

@Composable
fun ErrorScreen(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(text = message, modifier = Modifier.padding(bottom = 8.dp))
    }
}


@Composable
fun NotFoundScreen(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NotFoundScreenPreview() {
    WithFilmsTheme() {
        NotFoundScreen(
            modifier = Modifier.fillMaxSize(),
            message = "Error"
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    WithFilmsTheme() {
        ErrorScreen(
            message = "Error",
        )
    }
}
