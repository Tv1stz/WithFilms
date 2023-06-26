package com.example.withfilms.presentation.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.withfilms.R

@Composable
fun CustomSearch(
    hint: String,
    query: String,
    onChangeQuery: (String) -> Unit,
    hintTextStyle: TextStyle = TextStyle(
        color = Color.Gray,
        fontSize = 14.sp,
    ),
    inputTextStyle: TextStyle = TextStyle(
        color = Color.White,
        fontSize = 16.sp,
    ),
) {
    BasicTextField(
        value = query,
        onValueChange = onChangeQuery,
        textStyle = inputTextStyle,
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
            .height(55.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.surfaceVariant),
    ) { innerTextField ->
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp),
        ) {
            Box {
                if (query.isEmpty()) {
                    androidx.compose.material.Text(text = hint, style = hintTextStyle)
                }
                innerTextField()
            }
        }
    }
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
                text = stringResource(id = R.string.show_more),
                style = MaterialTheme.typography.labelMedium
            )
        }
    }
}

@Composable
fun RatingItem(
    rating: String
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(id = R.drawable.star),
            contentDescription = null
        )
        Spacer(modifier = Modifier.width(4.dp))
        Text(
            text = rating,
            color = Color(0xFFE2963B)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ShowMoreButtonPreview() {
    ShowMoreButton(
        onShowMoreClick = {}
    )
}