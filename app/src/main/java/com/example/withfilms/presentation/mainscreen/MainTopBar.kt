package com.example.withfilms.presentation.mainscreen

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.withfilms.R
import com.example.withfilms.presentation.ui.theme.WithFilmsTheme

@Composable
fun MainTopBar(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.headlineLarge
        )
    }
}

@Preview
@Composable
fun MainTopBarPreview() {
    WithFilmsTheme {
        MainTopBar()
    }
}