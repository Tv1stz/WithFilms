package com.example.withfilms.presentation.navigation.filmdetailgraph.screens

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.withfilms.presentation.filmdetail.SharedFilmDetailViewModel
import com.example.withfilms.presentation.filmdetail.filmdetailscreen.FilmDetailScreen
import com.example.withfilms.presentation.navigation.sharedViewModel


private const val filmIdKey = "filmId"

private const val filmDetailRoute = "filmDetail"

fun NavGraphBuilder.filmDetailScreen(
    navController: NavHostController
) {
    composable(
        route = filmDetailRoute,
    ) {
        val filmId = it.arguments?.getInt(filmIdKey) ?: 0

        val viewModel = it.sharedViewModel<SharedFilmDetailViewModel>(navController)
        LaunchedEffect(true) {
            viewModel.onStart(filmId)
        }
        val filmDetailState by viewModel.filmUiState.collectAsState()

        FilmDetailScreen(
            filmDetail = filmDetailState,
            onBackClick = navController::popBackStack,
            onDescriptionClick = {
                navController.navigateToDescription()
            },
            onShowMoreStaffClick = {
                navController.navigateToFilmStaff()
            }
        )
    }
}