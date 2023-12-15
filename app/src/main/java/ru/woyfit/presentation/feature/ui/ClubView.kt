package ru.woyfit.presentation.feature.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import ru.woyfit.domain.club.vo.ClubVO
import ru.neopharm.clubs.domain.club_category.vo.ClubCategoryVO
import ru.woyfit.presentation.feature.ui.custom_theme.Theme
import ru.woyfit.presentation.feature.ui.custom_theme.textStyle

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ClubView(
    club: ClubVO,
    onClubClick: (Int) -> Unit,
    onCategoryClick: (Int, Int) -> Unit,
    modifier: Modifier = Modifier
){
    val expanded = remember {
        mutableStateOf(false)
    }
    var angle by remember {
        mutableStateOf(0f)
    }

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .componentRegistry {
            add(SvgDecoder(LocalContext.current))
        }.build()

    val painter = rememberImagePainter(data = club.image, imageLoader)

    Box(modifier = modifier
        .background(
            color = Color(android.graphics.Color.parseColor(club.color)),
            shape = RoundedCornerShape(6.dp)
        )
        .clickable { onClubClick(club.id) },
        contentAlignment = Alignment.BottomEnd){


        Column(horizontalAlignment = Alignment.Start, modifier = Modifier
            .padding(start = 15.dp, end = 15.dp, top = 20.dp, bottom = 25.dp)) {
            Text(text = club.title, style = textStyle.copy(fontSize = 27.sp, color = Color.White))


            Text(text = club.description,
                style = textStyle.copy(fontSize = 13.sp, color = Color.White), modifier = Modifier
                    .fillMaxWidth(0.7f)
                    .padding(top = 10.dp))

            Column(modifier =
            Modifier.padding(top = if(expanded.value) 0.dp else 14.dp)) {
                club.categories.forEachIndexed {it, category ->
                    if(it != 0) {
                        AnimatedVisibility(
                            visible = expanded.value,
                            enter = slideInVertically() + expandVertically(expandFrom = Alignment.Bottom) + fadeIn(),
                            exit = slideOutVertically(targetOffsetY = {fullHeight ->  fullHeight}) + shrinkVertically() + fadeOut()
                        ) {
                            Text(category.title, style = textStyle.copy(fontSize = 13.sp, color = Color.White, fontWeight = FontWeight(600)), modifier = Modifier
                                .padding(top = if (1 == it) 12.dp else 0.dp)
                                .clickable { onCategoryClick(club.id, category.id) }
                                .drawBehind {
                                    val strokeWidthPx = 1.dp.toPx()

                                    val verticalOffset = size.height - 2.sp.toPx()
                                    drawLine(
                                        color = Color.White,
                                        strokeWidth = strokeWidthPx,
                                        start = Offset(0f, verticalOffset+4),
                                        end = Offset(size.width, verticalOffset+4)
                                    )
                                })
                        }
                    }
                }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                      Text(text= club.categories[0].title, style = textStyle
                          .copy(fontSize = 13.sp,
                              color = Color.White,
                              fontWeight = FontWeight(600),
                              lineHeight = 13.sp),
                          modifier = Modifier.drawBehind {
                              val strokeWidthPx = 1.dp.toPx()

                              val verticalOffset = size.height - 2.sp.toPx()
                              drawLine(
                                  color = Color.White,
                                  strokeWidth = strokeWidthPx,
                                  start = Offset(0f, verticalOffset+4),
                                  end = Offset(size.width, verticalOffset+4)
                              )
                          })


                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null, tint = Color.White,
                        modifier = Modifier
                            .rotate(angle)
                            .padding(start = 5.dp)
                            .clickable {
                                expanded.value = !expanded.value
                                angle = (angle + 180) % 360f
                            })
                }
            }
        }

        Image(painter = painter, contentDescription = null, contentScale = ContentScale.FillWidth,
            alignment = Alignment.TopCenter, modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(100.dp)
                .align(Alignment.BottomEnd))
    }
}


@Preview
@Composable
fun ClubPreview() {
    Theme {
        ClubView(
            ClubVO(
                id = 0,
                title = "Клуб",
                description = "Длинное описание",
                image = "https://f.stolichki.ru/s/media/articles/clubs/main/bg-1.png",
                color = "#FF498985",
                categories = listOf(
                    ClubCategoryVO(title = "О болезнях от А до Я"),
                    ClubCategoryVO(title = "Про лекарства"),
                    ClubCategoryVO(title = "На диете"),
                    ClubCategoryVO(title = "Психология"),
                    ClubCategoryVO(title = "Важно: Коронавирус"))
            ), {}, { i: Int, i1: Int -> }
        )

    }
}

