package com.example.withfilms.presentation.navigation.screens

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.withfilms.presentation.navigation.navigateToDetail
import com.example.withfilms.presentation.persondetail.PersonDetailScreen

private const val PERSON_ID_KEY = "personId"
private const val BASE_ROUTE = "personDetail"
private const val DETAIL_ROUTE = "$BASE_ROUTE/{$PERSON_ID_KEY}"

fun NavGraphBuilder.personDetailScreen(
    navController: NavHostController
) {
    composable(
        route = DETAIL_ROUTE,
        arguments = listOf(
            navArgument(PERSON_ID_KEY) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val personId = backStackEntry.arguments?.getInt(PERSON_ID_KEY) ?: 0

        PersonDetailScreen(
            onFilmClick = {
                navController.navigateToDetail(it)
            },
            onBackClick = navController::popBackStack,
            personId = personId
        )
    }
}

fun NavHostController.navigateToPersonDetail(personId: Int) {
    navigate("$BASE_ROUTE/$personId")
}