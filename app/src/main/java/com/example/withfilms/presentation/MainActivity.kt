package com.example.withfilms.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.withfilms.domain.model.BottomNavItem
import com.example.withfilms.presentation.navigation.FilmNavGraph
import com.example.withfilms.presentation.ui.theme.WithFilmsTheme
import com.example.withfilms.presentation.utils.CustomBottomAppBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WithFilmsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            CustomBottomAppBar(
                                items = listOf(
                                    BottomNavItem.home,
                                    BottomNavItem.search
                                ),
                                navController = navController,
                                onItemClick = { navController.navigate(it.route) }
                            )
                        }
                    ) {
                        Box(modifier = Modifier.padding(it)) {
                            FilmNavGraph(navController = navController)
                        }

                    }
                }
            }
        }
    }
}
