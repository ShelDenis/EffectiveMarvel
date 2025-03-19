package com.example.effectivemarvel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.effectivemarvel.ui.theme.EffectiveMarvelTheme
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EffectiveMarvelTheme() {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "choose_hero_screen") {
        composable("choose_hero_screen") { ChooseHeroScreen(navController) }
        composable("hero_screen_{heroName}", arguments = listOf(
            navArgument("heroName") {
                type = NavType.StringType
            }
        )) { backStackEntry ->
            val heroName = backStackEntry.arguments?.getString("heroName")
            HeroScreen(heroName.toString())
        }
    }
}