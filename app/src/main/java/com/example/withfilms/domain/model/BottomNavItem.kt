package com.example.withfilms.domain.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector,
) {
    companion object {

        val home = BottomNavItem(
            name = "Home",
            route = "films",
            icon = Icons.Default.Home
        )
        val search = BottomNavItem(
            name = "Search",
            route = "search",
            icon = Icons.Default.Search,
        )
    }
}
