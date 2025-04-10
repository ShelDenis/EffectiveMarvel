package com.example.effectivemarvel

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.effectivemarvel.ui.theme.White


@Composable
fun HeroScreen(navController: NavController, heroId: String, viewModel: MarvelCharacterViewModel) {
    val waitServer = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.loadCharacterById(heroId.toInt(), "1710250461",
            "5d103b1af37466dcc9374d4349a2c10f", "c357422eaa6746cdbb3a9bdf4d4a0a69")
    }

    val characterState = viewModel.characterState.collectAsState()

    if (characterState.value == null)
        waitServer.value = true
    else
        waitServer.value = false



    Column {
        characterState.value?.data?.results?.firstOrNull()?.let { character ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                if (waitServer.value)
                    Text(
                        text = "Waiting for server response...",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(vertical = 74.dp),
                        color = White
                    )

                AsyncImage(
                    model = character.thumbnail.path + "." + character.thumbnail.extension,
                    contentDescription = character.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_arrow_left),
                    contentDescription = "Arrow back",
                    modifier = Modifier
                        .size(56.dp, 64.dp)
                        .padding(top = 16.dp, start = 16.dp)
                        .clickable {
                            navController.navigate("choose_hero_screen") }
                )

                Column(modifier = Modifier
                    .padding(start = 28.dp, bottom = 40.dp)
                    .zIndex(1f)
                    .align(Alignment.BottomStart)
                ) {
                    Text(
                        text = character.name,
                        style = MaterialTheme.typography.bodyLarge,
                        color = White,
                    )

                    Spacer(modifier = Modifier
                        .height(20.dp))

                    Text(
                        text = character.description,
                        style = MaterialTheme.typography.titleLarge,
                        color = White,
                        modifier = Modifier.width(320.dp)
                    )
                }
            }
        }
    }
}