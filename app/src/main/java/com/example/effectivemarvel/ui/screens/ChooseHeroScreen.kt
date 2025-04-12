@file:OptIn(ExperimentalFoundationApi::class)

package com.example.effectivemarvel
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.effectivemarvel.ui.theme.White
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color
import androidx.compose.material3.Surface


@Composable
fun ChooseHeroScreen(navController: NavController,
                     viewModel: MarvelViewModel) {
    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
    val waitServer = remember { mutableStateOf(false) }
    val characters by viewModel.characters.collectAsState()

    var charList = characters

    if (charList.size > 0) {
        charList = charList.subList(12, 17)
        waitServer.value = false
    } else
        waitServer.value = true

    Box(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .drawTwoColoredBackground(heroColor = Color.Red)
        ) {}
    }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            AsyncImage(
                model = "https://iili.io/JMnuvbp.png",
                contentDescription = "marvel_logo",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .width(128.dp)
                    .height(27.dp)
            )

            Text(
                text = "Choose your hero",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 54.dp),
                color = White
            )

            if (waitServer.value)
                Text(
                    text = "Waiting for server response...",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(vertical = 74.dp),
                    color = White
                )

            LazyRow(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                state = lazyListState,
                contentPadding = PaddingValues(horizontal = 60.dp),
                flingBehavior = snapBehavior
            ) {

                items(charList.count(), key = { charList[it].id }) { index ->
                    val h = charList[index]
                    val shape = RoundedCornerShape(10.dp)
                    val height = 550.dp
                    val imagePath = h.thumbnail.path + "." + h.thumbnail.extension

                    Box(
                        modifier = Modifier
                            .height(height)
                            .fillMaxWidth()
                            .background(White, shape = shape)
                            .clickable {
                                navController.navigate("hero_screen_${h.id}")
                            },
                        contentAlignment = Alignment.BottomStart
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(start = 28.dp, bottom = 40.dp)
                                .zIndex(1f)
                        ) {
                            Text(
                                text = h.name,
                                style = MaterialTheme.typography.bodyLarge,
                                color = White,
                            )
                        }

                        AsyncImage(
                            model = imagePath,
                            contentDescription = h.name,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .width(270.dp)
                                .height(550.dp)
                                .align(Alignment.Center)
                                .clip(shape)
                        )
                    }

                }
            }
        }
    }
