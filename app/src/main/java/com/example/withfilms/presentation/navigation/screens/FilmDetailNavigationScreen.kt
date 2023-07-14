package com.example.withfilms.presentation.navigation.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.withfilms.presentation.filmdetail.FilmDetailScreen
import com.example.withfilms.presentation.filmdetail.FilmDetailViewModel
import com.example.withfilms.presentation.navigation.sharedViewModel
import com.example.withfilms.presentation.utils.ErrorScreen
import com.example.withfilms.presentation.utils.LoadingScreen
import com.example.withfilms.util.LoadState

private const val FILM_ID_KEY = "filmId"
private const val FILM_DETAIL_ROUTE = "filmDetail"

fun NavGraphBuilder.filmDetailScreen(
    navController: NavHostController
) {
    composable(
        route = FILM_DETAIL_ROUTE,
    ) {
        val filmId = it.arguments?.getInt(FILM_ID_KEY) ?: 0

        val viewModel = it.sharedViewModel<FilmDetailViewModel>(navController)

        LaunchedEffect(true) {
            viewModel.onStart(filmId)
        }

        val detail by viewModel.detailUiState.collectAsState()

        when (detail.loadState) {
            LoadState.ERROR -> {
                ErrorScreen(modifier = Modifier.fillMaxSize(), onRefreshClick = {})
            }

            LoadState.LOADING -> {
                LoadingScreen(modifier = Modifier.fillMaxSize())
            }

            LoadState.SUCCESS -> {
                FilmDetailScreen(
                    detail = detail,
                    onBackClick = navController::popBackStack,
                    onPersonClick = { personId -> navController.navigateToPersonDetail(personId) },
                    onShowMoreClick = {
                        navController.navigateToFilmStaff()
                    }
                )
            }
        }
    }
}