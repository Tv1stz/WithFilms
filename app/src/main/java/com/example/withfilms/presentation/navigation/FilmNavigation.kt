package com.example.withfilms.presentation.navigation

import androidx.navigation.NavHostController
import com.example.withfilms.presentation.navigation.FilmDestination.FILMS_SCREEN_ROUTE
import com.example.withfilms.presentation.navigation.Screen.FILMS_SCREEN
import com.example.withfilms.presentation.navigation.Screen.FILM_DETAIL_SCREEN

private object Screen {
    const val FILMS_SCREEN = "films"
    const val FILM_DETAIL_SCREEN = "filmDetail"
    const val ACTOR_DETAIL = "actorDetail"
}

object FilmDestinationArgs {
    const val FILM_ID = "filmId"
    const val TITLE_FILM = "title"
}

object FilmDestination {
    const val FILMS_SCREEN_ROUTE = FILMS_SCREEN
}


fun NavHostController.navigateToFilms() {
    navigate(FILMS_SCREEN_ROUTE)
}

fun NavHostController.navigateToFilmDetail(id: Long) {
    navigate("$FILM_DETAIL_SCREEN/$id")
}
