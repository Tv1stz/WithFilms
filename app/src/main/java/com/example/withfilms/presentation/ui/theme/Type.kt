package com.example.withfilms.presentation.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.withfilms.R

val JotiOne = FontFamily(
    Font(R.font.jotione_regular)
)

val Lato = FontFamily(
    Font(R.font.lato_regular),
    Font(R.font.lato_bold, FontWeight.Bold),
    Font(R.font.lato_italic, FontWeight.Normal, FontStyle.Italic),
    Font(R.font.lato_bolditalic, FontWeight.Bold, FontStyle.Italic)
)


// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    //текст карточек
    titleSmall = TextStyle(
        fontFamily = Lato,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),

    //название фильма в деталях
    titleMedium = TextStyle(
        fontFamily = Lato,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal
    ),

    //для clips
    labelMedium = TextStyle(
        fontFamily = Lato,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    ),
    //Описание фильма
    bodySmall = TextStyle(
        fontFamily = Lato,
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic
    ),
    //Читать далее
    bodyMedium = TextStyle(
        fontFamily = Lato,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Italic
    ),
    titleLarge = TextStyle(
        fontFamily = JotiOne,
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal,
    )
)