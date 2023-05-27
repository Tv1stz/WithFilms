package com.example.withfilms.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.withfilms.presentation.filmdetailscreen.FilmDetailScreen
import com.example.withfilms.presentation.filmsscreen.FilmsScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmNavGraph(
    navController: NavHostController,
    startDestination: String = FilmDestination.FILMS_SCREEN_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            FilmDestination.FILMS_SCREEN_ROUTE
        ) {
            FilmsScreen(
                onFilmClick = {
                    navController.navigateToFilmDetail(
                        id = it,
                    )
                }
            )
        }
        composable(
            route = "filmDetail/{filmId}",
            arguments = listOf(
                navArgument("filmId") { type  = NavType.LongType},
            )
        ) {backStackEntry ->
            val id = backStackEntry.arguments?.getLong("filmId") ?: 0

            FilmDetailScreen(
                id = id,
                onBackClick = navController::popBackStack
            )
        }
    }
}

