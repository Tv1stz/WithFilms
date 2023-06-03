package com.example.withfilms.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField

import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.withfilms.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    userRequest: String,
    onUserRequestChanged: (String) -> Unit
) {
    TextField(
        modifier = Modifier
            .height(55.dp)
            .padding(horizontal = 11.dp)
            .fillMaxWidth()
            .clip(
                RoundedCornerShape(16.dp)
            ),
        value = userRequest,
        onValueChange = onUserRequestChanged,
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = MaterialTheme.colorScheme.surfaceVariant,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        supportingText = { Text(
            text = "Search",
            color = Color(0xFF868686),
            fontSize = 12.sp
        ) },
        trailingIcon = {
            Image(
                painter = painterResource(id = R.drawable.request),
                contentDescription = null
            )
        },

    )
}

@Composable
fun ShowMoreButton(
    onShowMoreClick: () -> Unit
) {
    Box(
        modifier = Modifier.width(86.dp).height(120.dp).clickable { onShowMoreClick() },
        contentAlignment = Alignment.Center,
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.show_more_arrow),
                contentDescription = null
            )
            Text(
                modifier = Modifier.padding(top = 4.dp),
                text = "Показать еще",
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ShowMoreButtonPreview() {
    ShowMoreButton(
        onShowMoreClick = {}
    )
}