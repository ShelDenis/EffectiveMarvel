package com.example.effectivemarvel

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font


val InterFont = FontFamily(
    Font(R.font.inter,FontWeight.Normal),
    Font(R.font.inter,FontWeight.Bold)
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)

//val MyCustomTypography = Typography(
//    bodyLarge = TextStyle(
//        fontFamily = InterFont,
//        fontWeight = FontWeight.Bold,
//        fontSize = 28.sp,
//        color = MaterialTheme.colorScheme.primary
//    ),
//
//    titleLarge = TextStyle(
//        fontFamily = InterFont,
//        fontWeight = FontWeight.Normal,
//        fontSize = 22.sp,
//        color = MaterialTheme.colorScheme.primary
//    )
//)

@Composable
fun myCustomTypography(): Typography {
    return Typography(
        bodyLarge = TextStyle(
            fontFamily = InterFont,
            fontWeight = FontWeight.Bold,
            fontSize = 28.sp,
            color = MaterialTheme.colorScheme.primary
        ),

        titleLarge = TextStyle(
            fontFamily = InterFont,
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            color = MaterialTheme.colorScheme.primary
        )
    )
}