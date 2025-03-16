package com.example.effectivemarvel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.effectivemarvel.ui.theme.White

@Composable
fun ChooseHeroScreen(navController: NavController) {
    var heroes: List<Hero> = listOf(Hero("Deadpool",
        "https://iili.io/JMnAfIV.png",
        "Please don't make the super suits green...or animated!"),
        Hero("Iron Man", "https://iili.io/JMnuDI2.png", "I AM IRON MAN"),
        Hero("Spider Man", "https://iili.io/JMnuyB9.png", "In iron suit"))

    Box(
        modifier = with (Modifier){
            fillMaxSize()
                .paint(
                    painterResource(id = R.drawable.ic_main_background),
                    contentScale = ContentScale.FillBounds)
        })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
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

        LazyRow(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            for (h in heroes) {
                item {
//                    Column(
//                        modifier = Modifier
//                            .fillMaxSize()
//                            .padding(30.dp),
//                        horizontalAlignment = Alignment.CenterHorizontally,
//                        verticalArrangement = Arrangement.Top
//                    ) {
//                        AsyncImage(
//                            model = h.img_ref,
//                            contentDescription = h.name,
//                            modifier = Modifier
//                                .width(300.dp)
//                                .height(550.dp)
//                        )
//
//                        Text(
//                            text = h.name,
//                            style = MaterialTheme.typography.bodyLarge,
//                            modifier = Modifier.padding(vertical = 0.dp),
//                            color = White
//                        )
//                    }
                    val shape =  RoundedCornerShape(10.dp)
                    val height = 550.dp
                    Box(
                        modifier = Modifier
                            .height(height)
                            .fillMaxWidth()
                            .background(White, shape = shape),
                        contentAlignment = Alignment.Center,
                    ) {
                        AsyncImage(
                            model = h.img_ref,
                            contentDescription = h.name,
                            modifier = Modifier
                                .width(270.dp)
                                .height(550.dp)
                                .clip(shape)
                        )

                        Text(
                            text = h.name,
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(vertical = 50.dp),
                            color = White
                        )

                    }
                }
            }
        }
    }
}