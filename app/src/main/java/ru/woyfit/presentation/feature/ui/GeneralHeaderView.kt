package ru.woyfit.presentation.feature.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import ru.woyfit.R
import ru.neopharm.clubs.domain.FakeVOs
import ru.woyfit.domain.club.vo.ClubVO
import ru.neopharm.clubs.domain.club.vo.ClubsVO

import ru.neopharm.clubs.feature.ui.compose.custom_theme.ExtendedJetTheme
import ru.neopharm.clubs.feature.ui.compose.custom_theme.GreyDeep
import ru.woyfit.presentation.feature.main.SubscribeClubListener
import ru.woyfit.presentation.feature.ui.custom_theme.MainTheme

@Composable
fun GeneralHeaderView(
    clubVO: ClubVO,
    clubs: ClubsVO,
    onClubClick: (Int)-> Unit,
    onBackClick: ()-> Unit,
    onSubscribeTopBarClick: () -> Unit,
    isClubFavorite: State<Boolean>,
    listFavorites: List<Int> = emptyList(),
    subscribeListener: SubscribeClubListener
){
    TopAppBar(
        contentColor = contentColorFor(Color(0xFFFFFFFF)),
        backgroundColor = Color.White,
        modifier = Modifier.height(110.dp)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)) {
            ClubHeaderView(clubVO = clubVO, clubs = clubs.clubs, listFavorites = listFavorites, subscribeListener = subscribeListener )

            FilterHeaderWithVO(
                clubs = clubs,
                currentClub = clubVO,
                onClubClick = onClubClick,
                onBackClick = onBackClick,
                onSubscribeClick = onSubscribeTopBarClick,
                isClubFavorite = isClubFavorite.value
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ClubHeaderPreview() {
    MainTheme {
        GeneralHeaderView(
            clubs = ClubsVO(clubs = FakeVOs.getClubsVO()),
            clubVO = FakeVOs.getClubsVO()[1],
            onBackClick = {},
            onClubClick = {},
            onSubscribeTopBarClick = {},
            isClubFavorite = remember { mutableStateOf(true) },
            listFavorites = listOf(),
            subscribeListener = object : SubscribeClubListener{
                override fun onClubSubscribeClick(clubId: Int) {

                }

                override fun onClubUnsubscribeClick(clubId: Int) {

                }

                override fun mailingAction(actionMailing: SettingMailing) {

                }

            }
        )
    }
}

@Composable
fun ClubHeaderView(clubVO: ClubVO, clubs: List<ClubVO>, listFavorites: List<Int>, subscribeListener: SubscribeClubListener){
    val expandSetting = remember {
        mutableStateOf(false)
    }

    val expandSubMenu = remember {
        mutableStateOf(false)
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val imageLoader = ImageLoader.Builder(LocalContext.current)
                .componentRegistry {
                    add(SvgDecoder(LocalContext.current))
                }.build()

            CompositionLocalProvider(LocalImageLoader provides imageLoader) {
                val painter = rememberImagePainter(clubVO.logoColor)

                Icon(
                    painter = painter,
                    contentDescription = "",
                    tint = Color(parseColor(clubVO.accentColor1)),
                    modifier = Modifier
                        .padding(1.dp)
                        .height(30.dp))
            }
        }
        Box {
            Icon(painter = painterResource(id = R.drawable.ic_setting), contentDescription = "", tint = Color(
                parseColor(clubVO.accentColor1)
            ), modifier =
            Modifier
                .padding(1.dp)
                .width(22.dp)
                .height(22.dp)
                .clickable {
                    expandSetting.value = true
                })

            // setting menu
            DropdownMenu(expanded = expandSetting.value, onDismissRequest = { expandSetting.value = false },
                modifier = Modifier
                    .width(306.dp)) {
                DropdownMenuItem(onClick = { expandSetting.value = false
                    expandSubMenu.value = true}, modifier = Modifier
                    .fillMaxWidth()) {
                    Text(
                        text = stringResource(R.string.subscribe_settings),
                        style = ExtendedJetTheme.typography.body.copy(
                            fontSize = 16.sp,
                            color = GreyDeep,
                        ),
                        modifier = Modifier
                            .padding(start = 16.dp, top = 15.dp, bottom = 15.dp)
                    )
                }
            }
            // Subscribe drop menu
            DropSubSettings(expandSubMenu, clubs, listFavorites, subscribeListener)
        }
    }
}

@Composable
fun FilterHeader(club: ClubVO, onClubClick: (Int) -> Unit, onBackClick: ()-> Unit){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 25.dp)
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = "",
            tint = Color(parseColor(club.accentColor1)),
            modifier = Modifier
                .padding(1.dp)
                .width(22.dp)
                .height(22.dp)
                .clickable { onBackClick() })
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .width(149.dp)
                .height(22.dp)
                .background(
                    color = Color(parseColor(club.accentColor1)),
                    shape = RoundedCornerShape(size = 4.dp)
                )
                .padding(start = 12.dp, top = 2.dp, end = 11.dp, bottom = 2.dp)
        ) {
        Text(
                text = stringResource(id = R.string.subscribe_title),
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = Color.White,
                )
            )
        }
        DropMenuClubs(onClubClick)
    }
}


@Composable
fun FilterHeaderWithVO(
    clubs: ClubsVO,
    currentClub: ClubVO,
    onClubClick: (Int) -> Unit,
    onBackClick: ()-> Unit,
    onSubscribeClick: () -> Unit,
    isClubFavorite: Boolean
){
    Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(top = 25.dp)
            .fillMaxWidth()) {
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_left),
            contentDescription = "",
            tint = Color(parseColor(currentClub.accentColor1)),
            modifier = Modifier
                .padding(1.dp)
                .width(22.dp)
                .height(22.dp)
                .clickable { onBackClick() }
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = if (isClubFavorite) {
                Modifier
                    .width(149.dp)
                    .height(22.dp)
                    .border(
                        1.dp,
                        color = Color(parseColor(currentClub.accentColor1)),
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(start = 12.dp, top = 2.dp, end = 11.dp, bottom = 2.dp)
            } else Modifier
                .width(149.dp)
                .height(22.dp)
                .background(
                    color = Color(parseColor(currentClub.accentColor1)),
                    shape = RoundedCornerShape(size = 4.dp)
                )
                .padding(start = 12.dp, top = 2.dp, end = 11.dp, bottom = 2.dp)
                .clickable { onSubscribeClick() }
        ) {
            Text(
                text = stringResource(id = if (isClubFavorite) R.string.subscribed_title
                    else R.string.subscribe_title),
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight(500),
                    color = if (!isClubFavorite) Color.White
                    else Color(parseColor(currentClub.accentColor1)),
                )
            )
        }
        DropMenuClubsWithVO(clubs = clubs, onClubClick = onClubClick)
    }
}

@Composable
fun DropMenuClubsWithVO(clubs: ClubsVO, onClubClick: (Int) -> Unit){
    val rotation = remember { Animatable(initialValue = 360f) }

    val expandedMenu = remember {
        mutableStateOf(false)
    }
    Box {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            modifier = Modifier.clickable {
                expandedMenu.value = !expandedMenu.value
            }) {
            Text(
                text = stringResource(id = R.string.clubs_others_title),
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF414E5C),
                )
            )
            Icon(painter = painterResource(id = R.drawable.ic_down_arrow),
                contentDescription = "",
                tint = Color.Black,
                modifier =
                Modifier
                    .padding(1.dp)
                    .width(16.dp)
                    .height(16.dp)
                    .graphicsLayer {
                        rotationX = rotation.value
                    })
        }
        DropdownMenu(
            expanded = expandedMenu.value,
            onDismissRequest = { expandedMenu.value = false },
            modifier = Modifier
                .width(220.dp)
                .align(Alignment.TopEnd)
        ) {
            clubs.clubs.forEach {
                OtherClubDropItemWithVO(it, onClubClick) {
                    expandedMenu.value = false
                }
            }
        }
        LaunchedEffect(key1 = expandedMenu.value, block = {
            rotation.animateTo(
                targetValue = if (expandedMenu.value) 180f else 0f,
                animationSpec = tween(300)
            )
            println(expandedMenu.value)
        })
    }
}

@Composable
fun OtherClubDropItemWithVO(
    clubVO: ClubVO,
    onClubClick: (Int) -> Unit,
    dismissRequest: () -> Unit
){
    DropdownMenuItem(
        onClick = {
            onClubClick(clubVO.id)
            dismissRequest() },
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(start = 5.dp)) {
        Box {
            val imageLoader = ImageLoader.Builder(LocalContext.current)
                .componentRegistry {
                    add(SvgDecoder(LocalContext.current))
                }.build()

            CompositionLocalProvider(LocalImageLoader provides imageLoader) {
                val painter = rememberImagePainter(clubVO.logoColor)

                Icon(
                    painter = painter,
                    contentDescription = "",
                    modifier = Modifier
                        .padding(1.dp)
                        .height(20.dp),
                    tint = Color(parseColor(clubVO.accentColor1))
                )
            }
        }
    }
}

@Composable
fun DropMenuClubs(onClubClick: (Int) -> Unit){

    val clubSimpleList = listOf(
        DropItemClub(clubId = 1, categoryId = 1, title = "Я-Мама", icon = R.drawable.ic_mother_category, color = "#E0C98C"),
        DropItemClub(clubId = 2, categoryId = 7, title = "красоте", icon = R.drawable.ic_big_o, color = "#ED9B6E"),
        DropItemClub(clubId = 3, categoryId = 13, title = "Моё здоровье", icon = R.drawable.ic_my_heart, color = "#4DAB8F")
    )
    val rotation = remember { Animatable(initialValue = 360f) }

    val expandedMenu = remember {
        mutableStateOf(false)
    }
    Box() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.Start),
            modifier = Modifier.clickable {
                expandedMenu.value = !expandedMenu.value
            }) {
            Text(
                text = "Другие клубы",
                style = TextStyle(
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF414E5C),
                )
            )
            Icon(painter = painterResource(id = R.drawable.ic_down_arrow),
                contentDescription = "",
                tint = Color.Black,
                modifier =
                Modifier
                    .padding(1.dp)
                    .width(16.dp)
                    .height(16.dp)
                    .graphicsLayer {
                        rotationX = rotation.value
                    })
        }
        DropdownMenu(
            expanded = expandedMenu.value,
            onDismissRequest = { expandedMenu.value = false },
            modifier = Modifier
                .width(220.dp)
                .align(Alignment.TopEnd)
        ) {
            clubSimpleList.forEach {
                OtherClubDropItem(it, onClubClick) {
                    expandedMenu.value = false
                }
            }
        }
        LaunchedEffect(key1 = expandedMenu.value, block = {
            rotation.animateTo(
                targetValue = if (expandedMenu.value) 180f else 0f,
                animationSpec = tween(300)
            )
            println(expandedMenu.value)
        })
    }
}
@Composable
fun OtherClubDropItem(dropItemClub: DropItemClub,
                      onClubClick: (Int) -> Unit, dismissRequest: () -> Unit){

    DropdownMenuItem(onClick = {
        onClubClick(dropItemClub.clubId)
        dismissRequest()
    }, modifier = Modifier
        .fillMaxWidth()
        .background(color = Color(0xFFFFFFFF))
        .padding(start = 5.dp)) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(15.dp, Alignment.Start),
            verticalAlignment = Alignment.Top,
        ) {
            Icon(painter = painterResource(id = dropItemClub.icon), contentDescription = "",  modifier = Modifier
                .padding(1.dp)
                .width(17.dp)
                .height(18.dp), tint = Color(parseColor(dropItemClub.color))
            )
            Text(
                text = dropItemClub.title,
                style = TextStyle(
                    fontSize = 16.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontWeight = FontWeight(600),
                    color = Color(parseColor(dropItemClub.color)),
                )
            )
        }
    }
}

data class DropItemClub(
    val clubId: Int,
    val categoryId: Int,
    val icon: Int,
    val title: String,
    val color: String
)
