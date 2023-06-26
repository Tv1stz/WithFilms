package com.example.withfilms.presentation.navigation.filmdetailgraph.screens

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.withfilms.presentation.persondetail.PersonDetailScreen
import com.example.withfilms.presentation.navigation.filmdetailgraph.navigateToDetail

private const val ACTOR_ID_KEY = "actorId"
private const val BASE_ROUTE = "actorDetail"
private const val DETAIL_ROUTE = "$BASE_ROUTE/{$ACTOR_ID_KEY}"

fun NavGraphBuilder.actorDetailScreen(
    navController: NavHostController
) {
    composable(
        route = DETAIL_ROUTE,
        arguments = listOf(
            navArgument(ACTOR_ID_KEY) { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val actorId = backStackEntry.arguments?.getInt(ACTOR_ID_KEY) ?: 0

        PersonDetailScreen(
            onFilmClick = {
                navController.navigateToDetail(it)
            },
            onBackClick = navController::popBackStack,
            personId = actorId
        )
    }
}

fun NavHostController.navigateToActorDetail(actorId: Int) {
    navigate("$BASE_ROUTE/$actorId")
}