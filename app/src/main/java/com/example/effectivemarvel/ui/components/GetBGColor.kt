package com.example.effectivemarvel.ui.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.effectivemarvel.ui.theme.Grey
import com.example.effectivemarvel.ui.theme.LightGrey

@Composable
fun getBGColor(): Color {
    if (isSystemInDarkTheme())
        return Grey
    return LightGrey
}


