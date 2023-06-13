package com.example.withfilms.presentation.navigation.filmdetailgraph.screens

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.withfilms.presentation.filmdetail.SharedFilmDetailViewModel
import com.example.withfilms.presentation.filmdetail.filmstaffscreen.FilmStaffScreen
import com.example.withfilms.presentation.navigation.navigateToActorDetail
import com.example.withfilms.presentation.navigation.sharedViewModel

private const val filmStaffRoute = "filmStaff"

fun NavGraphBuilder.filmStaffScreen(
    navController: NavHostController
) {
    composable(
        route = filmStaffRoute
    ) {backStackEntry ->
        val viewModel = backStackEntry.sharedViewModel<SharedFilmDetailViewModel>(navController)
        val filmDetailState by viewModel.filmUiState.collectAsStateWithLifecycle()

        FilmStaffScreen(
            onBackClick = navController::popBackStack,
            filmName = filmDetailState.filmName,
            staff = filmDetailState.staff,
            onActorClick = { navController.navigateToActorDetail(it) }
        )
    }
}

fun NavHostController.navigateToFilmStaff() {
    navigate(filmStaffRoute)
}