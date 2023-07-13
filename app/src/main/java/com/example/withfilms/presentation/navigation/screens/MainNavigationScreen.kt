package com.example.withfilms.presentation.navigation.screens

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.withfilms.presentation.main.MainScreen
import com.example.withfilms.presentation.navigation.MAIN_DESTINATION
import com.example.withfilms.presentation.navigation.navigateToDetail

fun NavGraphBuilder.mainScreen(
    navController: NavHostController
) {
    composable(route = MAIN_DESTINATION) {
        MainScreen(
            onFilmClick = { filmId ->
                navController.navigateToDetail(filmId)
            },
            onSearchClick = {}
        )
    }
}