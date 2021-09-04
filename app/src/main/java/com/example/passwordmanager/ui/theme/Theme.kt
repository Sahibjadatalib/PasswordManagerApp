package com.example.passwordmanager.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape

private val DarkColorPalette = darkColors(
    primary = LightBlue200,
    primaryVariant = LightBlue500,
    background = Gray900,
    onPrimary = Color.Black,
    onBackground = Color.White,
    onError = ErrorRed
)

private val LightColorPalette = lightColors(
    primary = LightBlue600,
    primaryVariant = LightBlue900,
    background = Color.White,
    onPrimary = Color.White,
    onBackground = Color.Black,
    onError = ErrorRed
)

@Composable
fun Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    CompositionLocalProvider(
        LocalPaddings provides Paddings(),
        LocalElevation provides Elevation()
    ) {
        MaterialTheme(
            colors = colors,
            typography = Typography,
            shapes = Shapes,
            content = content
        )
    }

}


object Theme{

    val colors: Colors
    @Composable
    get() = MaterialTheme.colors

    val typography: Typography
    @Composable
    get() = MaterialTheme.typography

    val shapes: Shapes
    @Composable
    get() = MaterialTheme.shapes

    val paddings: Paddings
    @Composable
    get() = LocalPaddings.current

    val elevation: Elevation
    @Composable
    get() = LocalElevation.current

}
