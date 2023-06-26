package com.example.withfilms.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

private const val HOME = "Главное"
private const val SEARCH = "Поиск"


data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
) {
    companion object {

        val home = BottomNavItem(
            name = HOME,
            route = HOME_ROUTE,
            icon = Icons.Default.Home
        )
        val search = BottomNavItem(
            name = SEARCH,
            route = SEARCH_ROUTE,
            icon = Icons.Default.Search,
        )
    }
}
