package com.example.withfilms.presentation.navigation.screens

import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.withfilms.presentation.filmdetail.FilmDetailViewModel
import com.example.withfilms.presentation.filmdetail.FilmStaffScreen
import com.example.withfilms.presentation.navigation.sharedViewModel

private const val FILM_STAFF_ROUTE = "filmStaff"

fun NavGraphBuilder.filmStaffScreen(
    navController: NavHostController
) {
    composable(
        route = FILM_STAFF_ROUTE,
    ) {
        val viewModel = it.sharedViewModel<FilmDetailViewModel>(navController)
        val detail by viewModel.detailUiState.collectAsStateWithLifecycle()
        FilmStaffScreen(
            filmName = detail.filmDetail!!.name,
            staff = detail.filmStaffMap,
            onBackClick = navController::popBackStack,
            onPersonClick = { personId -> navController.navigateToPersonDetail(personId) },
        )
    }
}

fun NavHostController.navigateToFilmStaff() {
    navigate(FILM_STAFF_ROUTE)
}