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
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.material3.Button
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.ui.res.stringResource
import kotlin.math.abs


@Composable
fun ChooseHeroScreen(navController: NavController, viewModel: MarvelViewModel) {
    val lazyListState = rememberLazyListState()
    val snapBehavior = rememberSnapFlingBehavior(lazyListState = lazyListState)
    val waitServer = remember { mutableStateOf(false) }
    val characters by viewModel.characters.collectAsState()
    val errorState = viewModel.errorState.collectAsState(null)

    val progress = remember { mutableStateOf(0f) }

    val animatedProgress by animateFloatAsState(
        targetValue = progress.value,
        animationSpec = ProgressIndicatorDefaults.ProgressAnimationSpec
    )

    var charList: List<CharacterUI>
    charList = characters.map { it.asCharacterUI() }

    if (charList.size > 0) {
        charList = charList.subList(0, 5)
        waitServer.value = false
    } else if (errorState.value == null) {
        waitServer.value = true
    }

    val density = LocalDensity.current
    val screenWidthPx = with(density) { LocalConfiguration.current.screenWidthDp.dp.roundToPx() }.toFloat()

    Box(modifier = Modifier.fillMaxSize()) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .drawTwoColoredBackground(heroColor = Color.Red)
        ) {}

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
                text = stringResource(R.string.choose_hero),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(vertical = 54.dp),
                color = White
            )

            if (waitServer.value && errorState.value == null) {
                Text(
                    text = stringResource(R.string.server_wait),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(100.dp)
                        .align(Alignment.CenterHorizontally),
                    color = White
                )
                CircularProgressIndicator()

            } else if (errorState.value == null && !waitServer.value) {
                LazyRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    state = lazyListState,
                    contentPadding = PaddingValues(horizontal = 60.dp),
                    flingBehavior = snapBehavior
                ) {
                    itemsIndexed(charList, key = { index, item -> item.id }) { index, hero ->
                        val offset = lazyListState.layoutInfo.visibleItemsInfo.find { it.index == index }?.offset ?: 0
                        val centerOffset = ((screenWidthPx / 2) - (offset + screenWidthPx / 2)).coerceIn(-screenWidthPx / 2, screenWidthPx / 2)


                        var orientNum = 0.7f
                        if (OrientationIsHorizontal()) {
                            orientNum = 1f
                        }
                        val scaleFactor = lerp(orientNum, 1f, 1f - abs(centerOffset) / (screenWidthPx / 2))

                        val shape = RoundedCornerShape(10.dp)
                        val height = 550.dp
                        val imagePath = hero.img_path

                        Box(
                            modifier = Modifier
                                .graphicsLayer(scaleX = scaleFactor, scaleY = scaleFactor)
                                .height(height)
                                .width(270.dp)
                                .fillMaxWidth()
                                .background(White, shape = shape)
                                .clickable {
                                    navController.navigate("hero_screen/${hero.id}")
                                },
                            contentAlignment = Alignment.BottomStart
                        ) {
                            Column(
                                modifier = Modifier
                                    .padding(start = 28.dp, bottom = 40.dp)
                                    .zIndex(1f)
                            ) {
                                Text(
                                    text = hero.name,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = White,
                                )
                            }

                            AsyncImage(
                                model = imagePath,
                                contentDescription = hero.name,
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

            } else if (errorState.value != null) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(16.dp)
                ) {
                    errorState.value?.let { errorMessage ->
                        Text(
                            text = errorMessage,
                            style = MaterialTheme.typography.bodyLarge,
                            color = White,
                            modifier = Modifier.padding(16.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }

                    Button(onClick = {
                        waitServer.value = true
                        viewModel.clearErrorState()
                        viewModel.loadCharacters()
                    }) {
                        Text(text = stringResource(R.string.update), style = MaterialTheme.typography.titleLarge)
                    }
                }
            }
        }
    }
}