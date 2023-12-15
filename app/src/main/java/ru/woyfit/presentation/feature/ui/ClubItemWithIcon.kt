package ru.woyfit.presentation.feature.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import ru.neopharm.clubs.domain.FakeVOs
import ru.woyfit.domain.club.vo.ClubVO
import ru.woyfit.presentation.feature.ui.custom_theme.MainTheme


@Composable
fun ClubItemWithIcon(
    club: ClubVO,
    onClubClick: (clubId: Int) -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = Color(android.graphics.Color.parseColor(club.accentColor1)),
                shape = RoundedCornerShape(4.dp)
            )
            .clickable { onClubClick(club.id) },
        contentAlignment = Alignment.Center
    ) {
        val imageLoader = ImageLoader.Builder(LocalContext.current)
                .componentRegistry {
                    add(SvgDecoder(LocalContext.current))
                }.build()

        CompositionLocalProvider(LocalImageLoader provides imageLoader) {
            val painter = rememberImagePainter(club.logo3)

            Icon(
                painter = painter,
                contentDescription = "club icon",
                tint = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewClubItemWithIcon() {
    MainTheme {
        ClubItemWithIcon(FakeVOs.getClubsVO()[0], {})
    }
}