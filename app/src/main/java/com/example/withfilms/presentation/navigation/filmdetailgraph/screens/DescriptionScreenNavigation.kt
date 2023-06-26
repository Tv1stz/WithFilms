package com.example.withfilms.presentation.navigation.filmdetailgraph.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.withfilms.presentation.filmdetail.SharedFilmDetailViewModel
import com.example.withfilms.presentation.filmdetail.descriptionscreen.DescriptionScreen
import com.example.withfilms.presentation.navigation.sharedViewModel

private const val DESCRIPTION_ROUTE = "filmDescription"

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.descriptionScreen(
    navController: NavHostController
) {
    composable(
        route = DESCRIPTION_ROUTE,
    ) {
        val viewModel = it.sharedViewModel<SharedFilmDetailViewModel>(navController)
        val filmDetailState by viewModel.uiState.collectAsState()
        val description = filmDetailState.filmDetail!!.description

        DescriptionScreen(
            description = description,
            onBackClick = navController::popBackStack
        )
    }
}

fun NavHostController.navigateToDescription() {
    navigate(DESCRIPTION_ROUTE)
}