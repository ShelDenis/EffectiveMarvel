package com.example.effectivemarvel

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Navigation(
    marvelViewModel: MarvelViewModel,
    marvelCharacterViewModel: MarvelCharacterViewModel
) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "choose_hero_screen") {
        composable("choose_hero_screen") {
            ChooseHeroScreen(navController, marvelViewModel)
        }
        composable(
            route = "hero_screen/{heroId}",
            arguments = listOf(
                navArgument(name = "heroId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val heroId = backStackEntry.arguments?.getString("heroId")?.toString() ?: ""
            HeroScreen(navController, heroId, marvelCharacterViewModel)
        }
    }
}