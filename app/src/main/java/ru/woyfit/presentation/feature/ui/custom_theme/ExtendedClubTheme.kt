package ru.neopharm.clubs.feature.ui.compose.custom_theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

data class ExtendedClubColors(
    val titleText: Color,
    val primaryText: Color,
    val primaryBackground: Color,
    val secondaryText: Color,
    val secondaryBackground: Color,
    val tintColor: Color,
    val clubPrimaryColor: Color
)

data class ExtendedClubTypography(
    val heading: TextStyle,
    val h3: TextStyle,
    val body: TextStyle,
)

internal val LocalClubTypography = staticCompositionLocalOf<ExtendedClubTypography> {
    error("No typo provided")
}

internal val LocalClubColors = staticCompositionLocalOf<ExtendedClubColors> {
    error("No color provided")
}

object ExtendedJetTheme{
    internal val colors: ExtendedClubColors
        @Composable
        internal get() = LocalClubColors.current

    internal val typography: ExtendedClubTypography
        @Composable
        internal get() = LocalClubTypography.current
}



