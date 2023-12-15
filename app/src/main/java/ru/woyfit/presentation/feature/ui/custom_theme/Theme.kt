package ru.woyfit.presentation.feature.ui.custom_theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import ru.neopharm.clubs.feature.ui.compose.custom_theme.ExtendedClubTypography
import ru.neopharm.clubs.feature.ui.compose.custom_theme.LocalClubColors
import ru.neopharm.clubs.feature.ui.compose.custom_theme.LocalClubTypography
import ru.neopharm.clubs.feature.ui.compose.custom_theme.PrimaryColor
import ru.neopharm.clubs.feature.ui.compose.custom_theme.Purple200
import ru.neopharm.clubs.feature.ui.compose.custom_theme.Purple700
import ru.neopharm.clubs.feature.ui.compose.custom_theme.Teal200

import ru.woyfit.R
import ru.woyfit.presentation.feature.ui.theme.baseLightPalette

private val DarkColorPalette = darkColorScheme(
    primary = Purple200,
    inversePrimary = Purple700,
    secondary = Teal200
)

private val LightColorPalette = lightColorScheme(
    primary = PrimaryColor,
    inversePrimary = Purple700,
    secondary = Teal200

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun MainTheme(
    content: @Composable () -> Unit
){
    val colors = baseLightPalette
    val typography = ExtendedClubTypography(
        heading = TextStyle(
            fontSize = 22.sp,
            lineHeight = 22.sp,
            fontFamily = FontFamily(Font(R.font.alice_regular)),
            fontWeight = FontWeight(400),
            color = colors.titleText
        ),
        h3 = TextStyle(
            fontSize = 20.sp,
            lineHeight = 20.sp,
            fontFamily = FontFamily(Font(R.font.alice_regular)),
            fontWeight = FontWeight(400),
            color = colors.titleText
        ),
        body = TextStyle(
            fontSize = 16.sp,
            lineHeight = 24.sp,
            fontFamily = FontFamily(Font(R.font.alice_regular)),
            fontWeight = FontWeight(400),
        )
    )

    CompositionLocalProvider(
        LocalClubColors provides colors,
        LocalClubTypography provides typography,
        content = content
    )
}

@Composable
fun Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colorScheme = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}