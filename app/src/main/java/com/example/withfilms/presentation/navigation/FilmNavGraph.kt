package com.example.withfilms.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.withfilms.presentation.filmdetail.FilmDetailScreen
import com.example.withfilms.presentation.mainscreen.MainScreen

private const val MAIN_ROUTE = "mainScreen"
private const val BASE_FILM_DETAIL_ROUTE = "filmDetail"
private const val FILM_ID_KEY = "filmId"
private const val DETAIL_FILM_DETAIL_ROUTE = "$BASE_FILM_DETAIL_ROUTE/{$FILM_ID_KEY}"

@Composable
fun FilmNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MAIN_ROUTE
    ) {
        composable(route = MAIN_ROUTE) {
            MainScreen(
                onFilmClick = {
                    navController.navigateToFilmDetail(it)
                },
                onSearchClick = {}
            )
        }
        composable(
            route = DETAIL_FILM_DETAIL_ROUTE,
            arguments = listOf(
                navArgument(FILM_ID_KEY) { type = NavType.IntType }
            )
        ) {
            val filmId = it.arguments?.getInt(FILM_ID_KEY) ?: 0

            FilmDetailScreen(
                onBackClick = navController::popBackStack,
                onPersonClick = {},
                filmId = filmId
            )
        }
    }
}

fun NavHostController.navigateToFilmDetail(filmId: Int) {
    navigate("$BASE_FILM_DETAIL_ROUTE/$filmId")
}