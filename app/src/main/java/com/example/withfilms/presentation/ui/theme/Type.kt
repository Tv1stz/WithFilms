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
    //Название приложения
    headlineLarge = TextStyle(
        fontFamily = JotiOne,
        fontSize = 28.sp,
        fontWeight = FontWeight.Normal),

    //Заголовки экранов
    headlineMedium = TextStyle(
        fontFamily = Lato,
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold
    ),

    //остальные заголовки
    titleLarge = TextStyle(
        fontFamily = Lato,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold
    ),

    // фильмы в деталях
    titleMedium = TextStyle(
        fontFamily = Lato,
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold
    ),

    //фильмы, актеры
    titleSmall = TextStyle(
        fontFamily = Lato,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),

    //информация об актерах
    bodyLarge = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Bold,
        fontSize = 15.sp,
    ),

    //читать далее
    bodyMedium = TextStyle(
        fontFamily = Lato,
        fontSize = 14.sp,
        fontStyle = FontStyle.Italic
    ),

    //описание
    bodySmall = TextStyle(
        fontFamily = Lato,
        fontSize = 13.sp,
        fontWeight = FontWeight.Normal,
        fontStyle = FontStyle.Italic
    ),

    labelLarge = TextStyle(
        fontFamily = Lato,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal
    ),

    //остальной текст
    labelMedium = TextStyle(
        fontFamily = Lato,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal
    ),

    //нижнее меню
    labelSmall = TextStyle(
        fontFamily = Lato,
        fontSize = 8.sp,
        fontWeight = FontWeight.Normal
    )

)