package com.example.effectivemarvel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemarvel.ui.theme.EffectiveMarvelTheme
import androidx.core.view.WindowCompat
import android.view.WindowInsets
import android.view.WindowInsetsController



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)
        DatabaseProvider.initDb(applicationContext)

        val vm = ViewModelProvider(this).get(MarvelViewModel::class.java)
        val hvm = ViewModelProvider(this).get(MarvelCharacterViewModel::class.java)

        hideNavigationBar()

        setContent {
            EffectiveMarvelTheme() {
                Navigation(vm, hvm)
            }
        }
    }

    private fun hideNavigationBar() {
        window.insetsController?.let { controller ->
            controller.hide(WindowInsets.Type.navigationBars())
            controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }
}
