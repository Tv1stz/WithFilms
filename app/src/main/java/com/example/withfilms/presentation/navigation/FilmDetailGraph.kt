package com.example.withfilms.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
import com.example.withfilms.presentation.navigation.screens.filmDetailScreen
import com.example.withfilms.presentation.navigation.screens.filmStaffScreen

private const val FILM_ID_KEY = "filmId"
private const val BASE_ROUTE = "detail"
private const val DETAIL_ROUTE = "$BASE_ROUTE/{$FILM_ID_KEY}"
private const val START_DESTINATION = "filmDetail"


fun NavGraphBuilder.detailGraph(
    navController: NavHostController
) {
    navigation(
        route = DETAIL_ROUTE,
        startDestination = START_DESTINATION,
        arguments = listOf(navArgument(FILM_ID_KEY) { type = NavType.IntType })
    )
    {
        filmDetailScreen(navController)

        filmStaffScreen(navController)
    }
}

fun NavHostController.navigateToDetail(filmId: Int) {
    navigate("$BASE_ROUTE/$filmId")
}