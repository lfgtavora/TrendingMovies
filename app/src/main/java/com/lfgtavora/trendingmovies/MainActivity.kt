package com.lfgtavora.trendingmovies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.navigation.compose.rememberNavController
import com.lfgtavora.designsystem.ThemeState
import com.lfgtavora.designsystem.theme.TrendingMoviesTheme
import com.lfgtavora.trendingmovies.navigation.NavigationComponent

class MainActivity : ComponentActivity() {
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()
            TrendingMoviesTheme(darkTheme = ThemeState.darkModeState) {
                Surface(color = MaterialTheme.colors.background) {
                    NavigationComponent(navController)
                }
            }
        }
    }

}
