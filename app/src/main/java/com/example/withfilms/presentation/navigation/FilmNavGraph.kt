package com.example.withfilms.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.withfilms.presentation.filmsscreen.FilmsScreen
import com.example.withfilms.presentation.navigation.filmdetailgraph.detailGraph
import com.example.withfilms.presentation.navigation.filmdetailgraph.navigateToDetail
import com.example.withfilms.presentation.navigation.filmdetailgraph.screens.actorDetailScreen
import com.example.withfilms.presentation.searchscreen.SearchScreen

const val HOME_ROUTE = "home"
const val SEARCH_ROUTE = "search"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmNavGraph(
    navController: NavHostController,
    startDestination: String = HOME_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(HOME_ROUTE) {
            FilmsScreen(
                onFilmClick = { navController.navigateToDetail(it) }
            )
        }
        composable(SEARCH_ROUTE) {
            SearchScreen(
                onFilmClick = { navController.navigateToDetail(it) }
            )
        }
        detailGraph(navController)
        actorDetailScreen(navController)
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

