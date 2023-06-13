package com.example.withfilms.presentation.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.withfilms.presentation.actordetail.ActorDetailScreen
import com.example.withfilms.presentation.filmsscreen.FilmsScreen
import com.example.withfilms.presentation.navigation.filmdetailgraph.detailGraph
import com.example.withfilms.presentation.navigation.filmdetailgraph.navigateToDetail
import com.example.withfilms.presentation.searchscreen.SearchScreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmNavGraph(
    navController: NavHostController,
    startDestination: String = "films"
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable("films") {
            FilmsScreen(
                onFilmClick = { navController.navigateToDetail(it) }
            )
        }
        composable("search") {
            SearchScreen(
                onFilmClick = { navController.navigateToDetail(it) }
            )
        }

        detailGraph(navController)

        composable(
            route = "actorDetail/{actorId}",
            arguments = listOf(
                navArgument("actorId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val actorId = backStackEntry.arguments?.getInt("actorId") ?: 0

            ActorDetailScreen(
                onFilmClick = {
                    navController.navigateToDetail(it)
                },
                onBackClick = navController::popBackStack,
                actorId = actorId
            )
        }
    }
}

fun NavHostController.navigateToActorDetail(actorId: Int) {
    navigate("actorDetail/$actorId")
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

