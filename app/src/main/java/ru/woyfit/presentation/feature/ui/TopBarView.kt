package ru.woyfit.presentation.feature.ui

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import ru.woyfit.R
import ru.neopharm.clubs.domain.FilterClub
import ru.woyfit.domain.club.vo.ClubVO
import ru.neopharm.clubs.domain.subscription.SubscribeType

import ru.neopharm.clubs.feature.ui.compose.custom_theme.ExtendedJetTheme
import ru.neopharm.clubs.feature.ui.compose.custom_theme.Green
import ru.neopharm.clubs.feature.ui.compose.custom_theme.GreenRubin
import ru.neopharm.clubs.feature.ui.compose.custom_theme.GreyDarkDeep
import ru.neopharm.clubs.feature.ui.compose.custom_theme.GreyDeep
import ru.neopharm.clubs.feature.ui.compose.custom_theme.GreyTint

import ru.woyfit.presentation.feature.main.SubscribeClubListener
import ru.woyfit.presentation.feature.ui.custom_theme.textStyle

@Composable
fun TopBarView(
    onMenuItemClick: (FilterClub) -> Unit,
    onBackClick: () -> Unit,
    currentFilter: State<FilterClub>,
    listMailing: List<SettingMailing> = emptyList(),
    subscribeListener: SubscribeClubListener = object : SubscribeClubListener{
        override fun onClubSubscribeClick(clubId: Int) {

        }

        override fun onClubUnsubscribeClick(clubId: Int) {

        }

        override fun mailingAction(actionMailing: SettingMailing) {

        }
    },
    isFavoriteClubsExist: State<Boolean>,
    listFavorites: List<Int> = emptyList(),
    clubs: List<ClubVO> = emptyList()
) {
    TopAppBar(
        backgroundColor = Color.White,
    ) {
        val scope = rememberCoroutineScope()
        val rotation = remember { Animatable(initialValue = 360f) }
        var expandedMenu by remember {
            mutableStateOf(false)
        }

        val isFavoriteClubsExistState by remember {
            mutableStateOf(isFavoriteClubsExist)
        }

        val listItems = mutableListOf<FilterClub>().apply {
            add(FilterClub.AllClubs())
            if (isFavoriteClubsExistState.value) add(FilterClub.FavoriteClubs())
            add(FilterClub.FavoriteScreen())
        }

        val expandSetting = remember {
            mutableStateOf(false)
        }

        val expandSubMenu = remember {
            mutableStateOf(false)
        }

        val expandMailing = remember {
            mutableStateOf(false)
        }
        Row(horizontalArrangement = Arrangement.SpaceBetween,  modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp), verticalAlignment = Alignment.CenterVertically) {
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                .clickable {
                    expandedMenu = true
                }) {
                Icon(painter = painterResource(id = R.drawable.ic_back_product), contentDescription = "back", tint = GreyTint,
                    modifier = Modifier
                        .padding(1.dp)
                        .width(20.dp)
                        .height(20.dp)
                        .clickable {
                            onBackClick()
                        }
                )
                Text(
                    text = stringResource(R.string.all_clubs),
                    style = textStyle.copy(fontSize = 23.sp, color = GreyDeep),
                    modifier = Modifier.padding(start = 16.dp)
                )
                Icon(painter = painterResource(id = R.drawable.ic_all_filters), contentDescription = null,
                    tint = Green, modifier = Modifier
                        .padding(start = 12.dp)
                        .graphicsLayer {
                            rotationX = rotation.value
                        })
            }
            DropdownMenu(
                expanded = expandedMenu,
                onDismissRequest = { expandedMenu = false },
                modifier = Modifier.width(212.dp)
            ) {
                listItems.forEach { filter ->
                    DropFilterItem(
                        filter,
                        currentFilter.value,
                        onMenuItemClick = onMenuItemClick,
                        closeMenu = { expandedMenu = false }
                    )
                }
            }

            Box {
                Icon(painter = painterResource(id = R.drawable.ic_setting), contentDescription = null,
                    tint = GreyTint, modifier = Modifier
                        .clickable {
                            expandSetting.value = true
                        }
                        .align(Alignment.CenterEnd))

                // setting menu
                DropdownMenu(expanded = expandSetting.value, onDismissRequest = { expandSetting.value = false },
                    modifier = Modifier
                        .width(306.dp)) {
                    DropdownMenuItem(
                        onClick = { expandSetting.value = false
                            expandSubMenu.value = true},
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.subscribe_settings),
                            style = ExtendedJetTheme.typography.body.copy(
                                fontSize = 16.sp,
                                color = GreyDeep,
                            ),
                            modifier = Modifier
                                .padding(start = 16.dp, top = 15.dp, bottom = 15.dp)
                                .fillMaxSize()
                        )
                    }
                }
                // Subscribe drop menu
                DropSubSettings(expandSubMenu, clubs, listFavorites, subscribeListener)
            }

            LaunchedEffect(expandedMenu){
                rotation.animateTo(
                    targetValue = if (expandedMenu) 180f else 0f,
                    animationSpec = tween(durationMillis = 500),
                )
            }
        }
    }
}

@Composable
fun DropSubSettings(expandSubMenu: MutableState<Boolean>, clubs: List<ClubVO>, listFavorites: List<Int>, subscribeListener: SubscribeClubListener){

    DropdownMenu(expanded = expandSubMenu.value, onDismissRequest = { expandSubMenu.value = false }, modifier = Modifier.width(305.dp)) {
        clubs.forEachIndexed { index, clubVO ->
            val imageLoader = ImageLoader.Builder(LocalContext.current)
                .componentRegistry {
                    add(SvgDecoder(LocalContext.current))
                }
                .build()

            DropdownMenuItem(onClick = {  }) {
                val isFavorite: Boolean = clubVO.id in listFavorites
                Box(modifier = Modifier.fillMaxWidth()) {

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

                    val expandSubscribe = remember {
                        mutableStateOf(false)
                    }
                    Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .clickable {
                            expandSubscribe.value = true
                        }) {
                        Text(
                            text = if(isFavorite) stringResource(R.string.unsubscribe)
                            else stringResource(R.string.subscribe),
                            style = ExtendedJetTheme.typography.body.copy(
                                fontSize = 13.sp,
                                color = GreyDarkDeep
                            ), modifier = Modifier.clickable {
                                if(isFavorite){
                                    subscribeListener.onClubUnsubscribeClick(clubVO.id)
                                }
                                else{
                                    subscribeListener.onClubSubscribeClick(clubVO.id)
                                }
                            }
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun DropPushSettingMenu(expandPushMenu: MutableState<Boolean>, listMailing: List<SettingMailing>, subscribeListener: SubscribeClubListener){
    DropdownMenu(expanded = expandPushMenu.value, onDismissRequest = { expandPushMenu.value = false },modifier = Modifier.width(315.dp)) {
        listMailing.forEachIndexed { index, settingMailing ->
            DropdownMenuItem(onClick = {
                settingMailing.isMailing = !settingMailing.isMailing
                subscribeListener.mailingAction(settingMailing)
            }, modifier = Modifier.fillMaxWidth()) {
                Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                    Text(text = stringResource(id = if(!settingMailing.isMailing) settingMailing.titleResId else settingMailing.negativeTitleResId), style = ExtendedJetTheme.typography.h3.copy(
                        fontSize = 13.sp,
                        fontWeight = FontWeight(400),
                        color = GreyDeep,
                    ), modifier = Modifier.padding(start = 12.dp, top = 17.dp, bottom = 16.dp))
                    Icon(painter = painterResource(id = if (settingMailing.isMailing) R.drawable.ic_no_mailing_cross else R.drawable.ic_filter_checkmark), contentDescription = "",
                        tint = if(!settingMailing.isMailing) Color(0xFFA3A8BF) else Color(0xFFD5D5D6), modifier = Modifier
                            .padding(end = 8.dp)
                            .width(12.dp)
                            .height(12.dp))
                }
            }
        }
    }
}

@Composable
fun DropFilterItem(
    filter: FilterClub,
    currentFilter: FilterClub,
    onMenuItemClick: (FilterClub) -> Unit,
    closeMenu: () -> Unit
) {
    DropdownMenuItem(
        onClick = {
            onMenuItemClick(filter)
            closeMenu()
                  },
        modifier = Modifier
            .border(width = 1.dp, color = Color(0xFFF3F3F3))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = when (filter) {
                    is FilterClub.AllClubs -> stringResource(id = R.string.filter_club_all_clubs_title)
                    is FilterClub.FavoriteClubs -> stringResource(id = R.string.filter_club_favorite_clubs_title)
                    is FilterClub.FavoriteScreen -> stringResource(id = R.string.filter_club_favorite_screen_title)
                                    },
                style = textStyle.copy(
                    fontSize = 13.sp,
                    color = GreyDeep
                )
            )
            if (filter.id == currentFilter.id) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_filter_checkmark),
                    contentDescription = null,
                    tint = GreenRubin
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBarView() {
    MaterialTheme {
        TopBarView(onMenuItemClick = {}, {}, remember {
            mutableStateOf(FilterClub.AllClubs())
        }, listMailing = mutableListOf(SettingMailing.EmailMailing()),
            subscribeListener = object : SubscribeClubListener{
            override fun onClubSubscribeClick(clubId: Int) {}

            override fun onClubUnsubscribeClick(clubId: Int) {}

            override fun mailingAction(actionMailing: SettingMailing) {}
        }, remember { mutableStateOf(true) }, clubs = listOf())
    }
}

sealed class SettingMailing(){
    abstract val id: Int
    abstract val type:String
    abstract val titleResId: Int
    abstract val negativeTitleResId: Int
    abstract var isMailing: Boolean
    class EmailMailing: SettingMailing(){
        override val id: Int = 0
        override val type: String = SubscribeType.EMAIL.type
        override val titleResId: Int = R.string.mailing_of_clubs
        override val negativeTitleResId: Int = R.string.no_mailing_of_clubs
        override var isMailing: Boolean = false
    }
    class TelegramMailing: SettingMailing(){
        override val id: Int = 0
        override val type: String = SubscribeType.TELEGRAM.type
        override val titleResId: Int = R.string.mailing_of_telegram
        override val negativeTitleResId: Int = R.string.no_mailing_of_telegram
        override var isMailing: Boolean = false
    }
    class SmsMailing: SettingMailing(){
        override val id: Int = 0
        override val type: String = SubscribeType.SMS.type
        override val titleResId: Int = R.string.mailing_of_sms
        override val negativeTitleResId: Int = R.string.no_mailing_of_sms
        override var isMailing: Boolean = false
    }
    class PushMailing: SettingMailing(){
        override val id: Int = 0
        override val type: String = SubscribeType.PUSH.type
        override val titleResId: Int = R.string.receive_push_notification
        override val negativeTitleResId: Int = R.string.no_receive_push_notification
        override var isMailing: Boolean = false
    }
}

sealed class SettingSub{
    abstract val id: Int
    abstract val title: String
    abstract var isSubscribe: Boolean
    abstract val icon: String
    abstract val color: String
    class ClubMom(override var isSubscribe: Boolean = false): SettingSub() {
        override val id: Int = 1
        override val title: String = "Я — мама"
        override val icon: String = "https://f.stolichki.ru/s/clubs/icons/club-i-mama-icon-2.svg"
        override val color: String = "#E0C98C"
    }
    class ClubAboutBeauty(override var isSubscribe: Boolean = false): SettingSub() {
        override val id: Int = 2
        override val title: String = "О красоте"
        override val icon: String = "https://f.stolichki.ru/s/clubs/icons/club-o-krasote-icon-2.svg"
        override val color: String = "#ED9B6E"
    }
    class ClubHealth(override var isSubscribe: Boolean = false): SettingSub() {
        override val id: Int = 3
        override val title: String = "Моё здоровье"
        override val icon: String = "https://f.stolichki.ru/s/clubs/icons/club-moe-zdorovie-icon-2.svg"
        override val color: String = "#4DAB8F"
    }
}
