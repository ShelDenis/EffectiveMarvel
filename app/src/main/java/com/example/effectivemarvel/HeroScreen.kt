package com.example.effectivemarvel

//package com.example.effectivemarvel
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
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
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.effectivemarvel.ui.theme.White


@Composable
fun HeroScreen(navController: NavController,
               heroName: String) {
    var hRef: String = ""
    var hDescription: String = ""

    for (h in heroes) {
        if (h.name == heroName) {
            hRef = h.img_ref
            hDescription = h.describe
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        AsyncImage(
            model = hRef,
            contentDescription = heroName,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
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
                text = heroName,
                style = MaterialTheme.typography.bodyLarge,
                color = White,
            )

            Spacer(modifier = Modifier
                .height(20.dp))

            Text(
                text = hDescription,
                style = MaterialTheme.typography.titleLarge,
                color = White,
                modifier = Modifier.width(320.dp)
            )
        }
    }
}