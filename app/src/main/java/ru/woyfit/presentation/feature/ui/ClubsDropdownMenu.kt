package ru.woyfit.presentation.feature.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import ru.neopharm.clubs.domain.FakeVOs
import ru.woyfit.domain.club.vo.ClubVO
import ru.neopharm.clubs.feature.ui.compose.custom_theme.PaleGray
import ru.neopharm.clubs.feature.ui.compose.custom_theme.TextSecondaryLight
import ru.neopharm.clubs.feature.ui.compose.custom_theme.TextTitle
import ru.woyfit.R
import ru.woyfit.presentation.feature.ui.custom_theme.Theme


@Composable
fun ClubsDropdownMenu(
    modifier: Modifier,
    clubs: List<ClubVO>,
    onClubClick: (Int) -> Unit,
    filterId: Int
) {
    var isExpanded by remember {
        mutableStateOf(false)
    }

    var selectedClub by remember {
        mutableStateOf(clubs.firstOrNull { it.id == filterId } ?: ClubVO())
    }

    Box(
        modifier = modifier
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.clickable {
                    isExpanded = !isExpanded
                })
            {
                Text(
                    text = selectedClub.title,
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = if (selectedClub.title == stringResource(id = R.string.favorite_default_filter_title))
                            FontWeight(400) else FontWeight(600)
                    ),
                    color = if (selectedClub.accentColor1 == "#") TextTitle else Color(
                        android.graphics.Color.parseColor(
                            selectedClub.accentColor1
                        )
                    )
                )

                Spacer(modifier = Modifier.width(8.dp))

                val rotation = remember { Animatable(initialValue = 0f) }
                Icon(
                    painter = painterResource(R.drawable.ic_all_filters),
                    contentDescription = "",
                    Modifier
                        .graphicsLayer {
                            rotationX = rotation.value
                        },
                    tint = TextSecondaryLight,
                )

                LaunchedEffect(isExpanded){
                    rotation.animateTo(
                        targetValue = if (isExpanded) 180f else 0f,
                        animationSpec = tween(durationMillis = 500),
                    )
                }
            }

            DropdownMenu(
                modifier = Modifier
                    .fillMaxWidth(0.8f),
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false }
            ) {
                clubs.forEach { club ->

                    DropdownMenuItem(
                        modifier = Modifier.border(width = 0.5.dp, color = PaleGray),
                        onClick = {
                            onClubClick(club.id)
                            selectedClub = club
                            isExpanded = false
                        })
                    {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            if (club.id != 0) {
                                val imageLoader = ImageLoader.Builder(LocalContext.current)
                                    .componentRegistry {
                                        add(SvgDecoder(LocalContext.current))
                                    }
                                    .build()

                                CompositionLocalProvider(LocalImageLoader provides imageLoader) {
                                    val painter = rememberImagePainter(club.logoColor)
                                    if (club.color != "#") {
                                        Icon(
                                            modifier = Modifier.height(20.dp),
                                            painter = painter,
                                            contentDescription = "club icon",
                                            tint = Color(parseColor(club.accentColor1))
                                        )
                                    }
                                }
                            }

                            if (club.id == 0) {
                                Text(
                                    text = club.title,
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight(400)
                                    ),
                                    color = if (club.color == "#") TextTitle else Color(
                                        android.graphics.Color.parseColor(
                                            club.accentColor1
                                        )
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewClubsDropdownMenu() {
    Theme {
        ClubsDropdownMenu(
            clubs = FakeVOs.getClubsVO(),
            modifier = Modifier, onClubClick = {}, filterId = 0
        )
    }
}