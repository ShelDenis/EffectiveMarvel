package com.example.effectivemarvel

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import com.example.effectivemarvel.ui.theme.Grey


@Composable
fun Modifier.drawTwoColoredBackground(heroColor: Color): Modifier = this.then(
    Modifier.drawWithContent {
        val staticBackground = Path().apply {
            moveTo(0f, size.height)
            lineTo(size.width, size.height / 2.2f)
            lineTo(size.width, 0f)
            lineTo(0f, 0f)
            close()
        }

        val heroBackground = Path().apply {
            moveTo(0f, size.height)
            lineTo(size.width, size.height)
            lineTo(size.width, size.height / 2.2f)
            close()
        }

        drawContent()

        drawPath(path = staticBackground, color = Grey)
        drawPath(path = heroBackground, color = heroColor)
    }
)