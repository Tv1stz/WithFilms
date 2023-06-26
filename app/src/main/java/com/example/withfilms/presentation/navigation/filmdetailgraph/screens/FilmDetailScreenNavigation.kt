package com.example.withfilms.presentation.navigation.filmdetailgraph.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.withfilms.R
import com.example.withfilms.presentation.filmdetail.SharedFilmDetailViewModel
import com.example.withfilms.presentation.filmdetail.filmdetailscreen.FilmDetailScreen
import com.example.withfilms.presentation.navigation.sharedViewModel
import com.example.withfilms.presentation.utils.ErrorScreen
import com.example.withfilms.presentation.utils.LoadState.ERROR
import com.example.withfilms.presentation.utils.LoadState.LOADING
import com.example.withfilms.presentation.utils.LoadState.SUCCESS
import com.example.withfilms.presentation.utils.LoadingScreen


private const val FILM_ID_KEY = "filmId"
private const val FILM_DETAIL_ROUTE = "filmDetail"

fun NavGraphBuilder.filmDetailScreen(
    navController: NavHostController
) {
    composable(
        route = FILM_DETAIL_ROUTE,
    ) {
        val filmId = it.arguments?.getInt(FILM_ID_KEY) ?: 0

        val viewModel = it.sharedViewModel<SharedFilmDetailViewModel>(navController)
        LaunchedEffect(true) {
            viewModel.onStart(filmId)
        }
        val filmDetailState by viewModel.uiState.collectAsState()
        when(filmDetailState.loadState) {
            ERROR -> {
                ErrorScreen(
                    message = stringResource(id = R.string.something_went_wrong),
                    modifier = Modifier.fillMaxSize()
                )
            }
            LOADING -> {
                LoadingScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }
            SUCCESS -> {
                FilmDetailScreen(
                    detail = filmDetailState,
                    onBackClick = navController::popBackStack,
                    onDescriptionClick = {
                        navController.navigateToDescription()
                    },
                    onShowMoreStaffClick = {
                        navController.navigateToFilmStaff()
                    },
                    onActorDetail = { actorId ->
                        navController.navigateToActorDetail(actorId = actorId)
                    },
                )
            }
        }
    }
}