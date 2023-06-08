package com.example.withfilms.presentation.navigation.filmdetailgraph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument

import com.example.withfilms.presentation.navigation.filmdetailgraph.screens.descriptionScreen
import com.example.withfilms.presentation.navigation.filmdetailgraph.screens.filmDetailScreen
import com.example.withfilms.presentation.navigation.filmdetailgraph.screens.filmStaffScreen

private const val filmIdKey = "filmId"
private const val baseRoute = "detail"
private const val detailRoute = "$baseRoute/{$filmIdKey}"
private const val startDestination = "filmDetail"


fun NavGraphBuilder.detailGraph(
    navController: NavHostController
) {
    navigation(
        route = detailRoute,
        startDestination = startDestination,
        arguments = listOf(navArgument(filmIdKey) { type = NavType.IntType })
    )
    {
        filmDetailScreen(navController)

        descriptionScreen(navController)

        filmStaffScreen(navController)
    }
}

fun NavHostController.navigateToDetail(filmId: Int) {
    navigate("$baseRoute/$filmId")
}