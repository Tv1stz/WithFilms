package com.example.withfilms.presentation.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.withfilms.R
import com.example.withfilms.presentation.ui.theme.WithFilmsTheme

@Composable
fun ErrorScreen(
    modifier: Modifier = Modifier,
    onRefreshClick: () -> Unit
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.something_went_wrong),
            style = MaterialTheme.typography.titleLarge
        )
        OutlinedButton(onClick = { onRefreshClick() }) {
            Text(text = stringResource(id = R.string.refresh))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ErrorScreenPreview() {
    WithFilmsTheme {
        ErrorScreen(
            modifier = Modifier.fillMaxSize(),
            onRefreshClick = {}
        )
    }
}
