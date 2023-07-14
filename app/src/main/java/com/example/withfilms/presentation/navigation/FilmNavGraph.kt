package com.example.withfilms.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.withfilms.presentation.navigation.screens.mainScreen
 import com.example.withfilms.presentation.navigation.screens.personDetailScreen

const val MAIN_DESTINATION = "mainScreen"

@Composable
fun FilmNavGraph(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = MAIN_DESTINATION
    ) {
        mainScreen(navController)
        detailGraph(navController)
        personDetailScreen(navController)
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