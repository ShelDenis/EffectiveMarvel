package com.example.effectivemarvel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemarvel.ui.theme.EffectiveMarvelTheme
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val vm = ViewModelProvider(this).get(MarvelViewModel::class.java)
        val hvm = ViewModelProvider(this).get(MarvelCharacterViewModel::class.java)
        setContent {
            EffectiveMarvelTheme() {
                Navigation(vm, hvm)
            }
        }
    }
}

@Composable
fun Navigation(vm: ViewModel, hvm: ViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "choose_hero_screen") {
        composable("choose_hero_screen") { ChooseHeroScreen(navController, vm as MarvelViewModel) }
        composable("hero_screen_{heroId}", arguments = listOf(
            navArgument("heroId") {
                type = NavType.StringType
            }
        )) { backStackEntry ->
            val heroId = backStackEntry.arguments?.getString("heroId")
            HeroScreen(navController, heroId.toString(), hvm as MarvelCharacterViewModel)
        }
    }
}