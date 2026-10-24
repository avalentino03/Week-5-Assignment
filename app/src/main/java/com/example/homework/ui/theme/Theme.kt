package com.example.homework.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val BrownDark = Color(0xFF4E342E)
val Brown = Color(0xFF6D4C41)
val Tan = Color(0xFFE8D9C8)
val Cream = Color(0xFFFAF3EB)
val ErrorRed = Color(0xFFB3261E)

private val colors = lightColorScheme(
    primary = Brown,
    onPrimary = Color.White,
    background = Cream,
    surface = Cream,
    onBackground = BrownDark,
    onSurface = BrownDark,
    error = ErrorRed
)

@Composable
fun TugasTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = colors, content = content)
}