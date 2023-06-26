package com.example.withfilms.presentation.navigation.filmdetailgraph.screens

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.withfilms.presentation.filmdetail.SharedFilmDetailViewModel
import com.example.withfilms.presentation.filmdetail.filmstaffscreen.FilmStaffScreen
import com.example.withfilms.presentation.navigation.sharedViewModel

private const val FILM_STAFF_ROUTE = "filmStaff"

fun NavGraphBuilder.filmStaffScreen(
    navController: NavHostController
) {
    composable(
        route = FILM_STAFF_ROUTE
    ) {backStackEntry ->
        val viewModel = backStackEntry.sharedViewModel<SharedFilmDetailViewModel>(navController)
        val filmDetailState by viewModel.uiState.collectAsStateWithLifecycle()

        FilmStaffScreen(
            onBackClick = navController::popBackStack,
            filmName = filmDetailState.filmDetail!!.name,
            staff = filmDetailState.filmStaff,
            onActorClick = { navController.navigateToActorDetail(it) }
        )
    }
}

fun NavHostController.navigateToFilmStaff() {
    navigate(FILM_STAFF_ROUTE)
}