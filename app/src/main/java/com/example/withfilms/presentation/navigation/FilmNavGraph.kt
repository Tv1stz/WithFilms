package com.example.withfilms.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.withfilms.presentation.mainscreen.MainScreen
import com.example.withfilms.presentation.navigation.filmdetailnavigation.detailGraph
import com.example.withfilms.presentation.navigation.filmdetailnavigation.navigateToDetail

private const val MAIN_DESTINATION = "mainScreen"

@Composable
fun FilmNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MAIN_DESTINATION
    ) {
        composable(route = MAIN_DESTINATION) {
            MainScreen(
                onFilmClick = { filmId ->
                    navController.navigateToDetail(filmId)
                },
                onSearchClick = {}
            )
        }
        detailGraph(navController)
    }
}



@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
): T {
    val navGraphRoute = destination.parent?.route ?: return hiltViewModel()
    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return hiltViewModel(parentEntry)
}