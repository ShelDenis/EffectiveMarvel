package com.example.effectivemarvel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.example.effectivemarvel.ui.theme.EffectiveMarvelTheme
//import androidx.compose.material3.enableEdgeToEdge


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        DatabaseProvider.initDb(applicationContext)

        val vm = ViewModelProvider(this).get(MarvelViewModel::class.java)
        val hvm = ViewModelProvider(this).get(MarvelCharacterViewModel::class.java)
        setContent {
            EffectiveMarvelTheme() {
                Navigation(vm, hvm)
            }
        }
    }
}
