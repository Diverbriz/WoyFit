package ru.woyfit.presentation.feature.ui

import android.view.ViewTreeObserver
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.CheckboxDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.ImageLoader
import coil.compose.rememberImagePainter
import coil.decode.SvgDecoder
import kotlinx.coroutines.launch
import ru.woyfit.R
import ru.neopharm.clubs.domain.FakeVOs
import ru.woyfit.domain.club.vo.ClubVO
import ru.neopharm.clubs.feature.ui.compose.custom_theme.ExtendedJetTheme
import ru.woyfit.presentation.feature.ui.custom_theme.MainTheme
import ru.woyfit.presentation.feature.ui.custom_theme.textStyle

@Composable
@Preview(showBackground = true)
fun SubscribePreview() {
    MainTheme {
        SubscribeView(
            FakeVOs.getClubsVO()[0],
            mEmail = "",
            onSubscribeClick = {s: String -> }
        )
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
fun SubscribeView(
    club: ClubVO,
    onSubscribeClick: (String) -> Unit,
    modifier: Modifier = Modifier,
    isDialog: Boolean = false,
    mEmail: String = "",
    onCloseDialogClick: () -> Unit = {}
) {

    val email = remember {
        mutableStateOf(mEmail)
    }

    val isChecked = remember {
        mutableStateOf(false)
    }

    val isSubscribeClicked = remember {
        mutableStateOf(false)
    }

    //decoration icon
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .componentRegistry {
            add(SvgDecoder(LocalContext.current))
        }.build()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp

    val painter = rememberImagePainter(data = club.decoration3, imageLoader = imageLoader)

    val uncorrectedBorder = (
            if (!isValidEmail(email.value) && isSubscribeClicked.value) BorderStroke(
                1.dp,
                color = Color(0xFFF28187)
            )
            else BorderStroke(0.dp, color = Color(0xFFFFFFFF))
            )

    val keyboardController = LocalSoftwareKeyboardController.current

    // To prevent software keyboard overlaps content
    val bringIntoViewRequester = remember { BringIntoViewRequester() }
    val scope = rememberCoroutineScope()
    val view = LocalView.current
    DisposableEffect(view) {
        val listener = ViewTreeObserver.OnGlobalLayoutListener {
            scope.launch { bringIntoViewRequester.bringIntoView() }
        }
        view.viewTreeObserver.addOnGlobalLayoutListener(listener)
        onDispose { view.viewTreeObserver.removeOnGlobalLayoutListener(listener) }
    }

    Surface(
        modifier = modifier
            .bringIntoViewRequester(bringIntoViewRequester)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp)
    ) {

        Box(
            modifier = Modifier
                .background(Color(parseColor(club.accentColor3)))
        ) {
            Image(
                painter = painter,
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .width(screenWidth - 36.dp)
                    .height(350.dp)
                    .align(Alignment.Center)
            )

            Column(
                horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {

                if (isDialog) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onCloseDialogClick() },
                        contentAlignment = Alignment.TopEnd,
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_dialog_close),
                            contentDescription = "",
                            modifier = Modifier.padding(top = 15.dp),
                            tint = Color(parseColor(club.accentColor4))
                        )
                    }
                }

                Text(
                    text = stringResource(R.string.subscribe_to_new_article),
                    style = textStyle,
                    modifier = Modifier
                        .padding(top = 41.dp),
                    textAlign = TextAlign.Center
                )

                Column(
                    modifier = Modifier
                        .padding(top = 20.dp)
                        .border(
                            uncorrectedBorder,
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .wrapContentHeight()
                ) {
                    TextField(
                        value = email.value,
                        onValueChange = {
                            email.value = it
                        },
                        modifier = Modifier
                            .fillMaxWidth(),
                        textStyle = textStyle,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        keyboardActions = KeyboardActions(
                            onDone = { keyboardController?.hide() }),
                        placeholder = {
                            Text(
                                text = "Email",
                                style = textStyle.copy(
                                    color = Color(0xFF787878),
                                    fontSize = 15.sp
                                ),
                            )
                        },
                        label = null,
                        shape = RoundedCornerShape(4.dp),
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            disabledIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            backgroundColor = Color.White, textColor = textStyle.color
                        ),
                        singleLine = true
                    )
                    if (!isValidEmail(email.value) && isSubscribeClicked.value) {
                        Row(
                            modifier = Modifier
                                .background(
                                    Color(0xFFF28187),
                                    shape = RoundedCornerShape(
                                        bottomStart = 8.dp,
                                        bottomEnd = 8.dp
                                    )
                                )
                                .height(28.dp)
                                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_cross),
                                contentDescription = "",
                                modifier = Modifier
                                    .padding(start = 8.dp)
                                    .width(24.dp)
                                    .height(24.dp),
                                tint = Color.Unspecified
                            )

                            Text(
                                text = stringResource(R.string.uncorrect_email),
                                style = ExtendedJetTheme
                                    .typography.heading.copy(
                                        fontSize = 12.sp,
                                        lineHeight = 18.sp,
                                        color = Color(0xFFFFFFFF),
                                    ),
                            )
                        }
                    }
                }

                Button(
                    onClick = {
                        isSubscribeClicked.value = true
                        if (isValidEmail(email.value) && isChecked.value) {
                            onSubscribeClick(email.value)
                            keyboardController?.hide()
                        }
                    },
                    shape = RoundedCornerShape(8.dp),
                    elevation = ButtonDefaults.elevation(defaultElevation = 4.dp),
                    modifier = Modifier
                        .padding(top = 15.dp)
                        .fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color(parseColor(club.accentColor4)))
                ) {
                    Text(
                        text = stringResource(id = R.string.subscribe),
                        style = textStyle.copy(
                            fontSize = 15.34.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFFFFFFFF),
                        ),
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 15.dp, bottom = 38.dp)
                ) {
                    Checkbox(
                        checked = isChecked.value,
                        onCheckedChange = {
                            isChecked.value = it
                        },
                        colors = CheckboxDefaults.colors(
                            checkmarkColor = Color(parseColor(club.accentColor4)),
                            checkedColor = Color.White
                        ),
                        modifier = Modifier
                            .size(26.dp)
                    )
                    Column(modifier = Modifier.padding(start = 10.dp)) {
                        Text(
                            text = stringResource(R.string.consent_to_mailing_text),
                            style = ExtendedJetTheme.typography.body.copy(
                                fontSize = 14.sp,
                                lineHeight = 16.8.sp,
                                color = Color(0xFF414E5C),
                            ),
                        )

                        if (!isChecked.value) {
                            Text(
                                text = stringResource(R.string.required_field_notification),
                                style = ExtendedJetTheme.typography.body.copy(
                                    fontSize = 12.sp,
                                    lineHeight = 18.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    color = Color(0xFFF28187),
                                )
                            )
                        }
                    }
                }

            }

        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun LikeArticle(
    club: ClubVO,
    onSubscribeClick: (String) -> Unit,
    mEmail: String = "",
    onCloseDialogClick: () -> Unit
) {
    val email = remember {
        mutableStateOf(mEmail)
    }

    val isChecked = remember {
        mutableStateOf(false)
    }

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .componentRegistry {
            add(SvgDecoder(LocalContext.current))
        }.build()

    val painter = rememberImagePainter(data = club.decoration4, imageLoader = imageLoader)

    val uncorrectedBorder =
        if (!isValidEmail(email.value)) BorderStroke(1.dp, color = Color(0xFFF28187)) else
            BorderStroke(0.dp, color = Color(0xFFFFFFFF))

    val isSubscribeClicked = remember {
        mutableStateOf(false)
    }

    Dialog(
        onDismissRequest = { },
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {

        val keyboardController = LocalSoftwareKeyboardController.current

        Surface(
            modifier = Modifier
                .padding(top = 50.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            elevation = 8.dp
        ) {
            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopEnd) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Color(parseColor(club.accentColor3)),
                            RoundedCornerShape(8.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth(0.9f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Image(
                            painter = painter,
                            contentDescription = "",
                            contentScale = ContentScale.FillBounds,
                            modifier = Modifier
                                .padding(top = 25.dp)
                                .width(103.dp)
                                .height(73.dp)
                        )

                        Text(
                            text = stringResource(id = R.string.article_dialog_title),
                            style = ExtendedJetTheme
                                .typography.heading.copy(
                                    fontSize = 27.sp,
                                    color = Color(0xFF414E5C),
                                    textAlign = TextAlign.Center
                                ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )

                        Text(
                            text = stringResource(id = R.string.article_dialog_text),
                            style = ExtendedJetTheme
                                .typography.heading.copy(
                                    fontSize = 19.sp,
                                    color = Color(0xFF414E5C),
                                    textAlign = TextAlign.Center
                                ),
                            modifier = Modifier
                                .padding(top = 10.dp)
                                .fillMaxWidth()
                        )

                        Column(
                            modifier = Modifier
                                .padding(top = 20.dp)
                                .border(
                                    uncorrectedBorder,
                                    shape = RoundedCornerShape(size = 8.dp)
                                )
                                .wrapContentHeight()
                        ) {
                            TextField(
                                value = email.value,
                                onValueChange = {
                                    email.value = it
                                },
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textStyle = textStyle,
                                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                                keyboardActions = KeyboardActions(
                                    onDone = { keyboardController?.hide() }
                                ),
                                placeholder = {
                                    Text(
                                        text = "Email",
                                        style = textStyle.copy(
                                            color = Color(0xFF787878),
                                            fontSize = 15.sp
                                        ),
                                    )
                                },
                                label = null,
                                shape = RoundedCornerShape(4.dp),
                                colors = TextFieldDefaults.textFieldColors(
                                    focusedIndicatorColor = Color.Transparent,
                                    disabledIndicatorColor = Color.Transparent,
                                    unfocusedIndicatorColor = Color.Transparent,
                                    backgroundColor = Color.White, textColor = textStyle.color
                                ),
                                singleLine = true
                            )
                            if (!isValidEmail(email.value) && isSubscribeClicked.value) {
                                Row(
                                    modifier = Modifier
                                        .background(
                                            Color(0xFFF28187),
                                            shape = RoundedCornerShape(
                                                bottomStart = 8.dp,
                                                bottomEnd = 8.dp
                                            )
                                        )
                                        .height(28.dp)
                                        .fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_cross),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .padding(start = 8.dp)
                                            .width(24.dp)
                                            .height(24.dp),
                                        tint = Color.Unspecified)

                                    Text(
                                        text = stringResource(id = R.string.uncorrect_email),
                                        style = ExtendedJetTheme
                                            .typography.heading.copy(
                                                fontSize = 12.sp,
                                                lineHeight = 18.sp,
                                                color = Color(0xFFFFFFFF),
                                            ),
                                    )
                                }
                            }
                        }

                        Button(
                            onClick = {
                                isSubscribeClicked.value = true
                                if (isValidEmail(email.value) && isChecked.value) {
                                    onSubscribeClick(email.value)
                                    keyboardController?.hide()
                                }
                            },
                            shape = RoundedCornerShape(8.dp),
                            elevation = ButtonDefaults.elevation(defaultElevation = 4.dp),
                            modifier = Modifier
                                .padding(top = 15.dp)
                                .fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color(parseColor(club.accentColor4)))
                        ) {
                            Text(
                                text = stringResource(id = R.string.subscribe), 
                                style = textStyle
                                    .copy(
                                        color = Color(0xFFFFFFFF),
                                        fontSize = 15.sp,
                                        fontWeight = FontWeight(600)
                                    ),
                                modifier = Modifier.padding(vertical = 6.dp)
                            )
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp, bottom = 38.dp)
                        ) {
                            Checkbox(
                                checked = isChecked.value,
                                onCheckedChange = {
                                    isChecked.value = it
                                },
                                colors = CheckboxDefaults.colors(
                                    checkmarkColor = Color(0xFFC78B8B),
                                    checkedColor = Color.White
                                ),
                                modifier = Modifier
                                    .size(26.dp)
                            )
                            Column(modifier = Modifier.padding(start = 10.dp)) {
                                Text(
                                    text = "Я даю согласие на получение \n" +
                                            "рекламно-информационной рассылки  (email).",
                                    style = textStyle,
                                )

                                if (!isChecked.value) {
                                    Text(
                                        text = "Это поле является обязательным",
                                        style = ExtendedJetTheme.typography.body.copy(
                                            fontSize = 12.sp,
                                            lineHeight = 18.sp,
                                            fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                            color = Color(0xFFF28187),
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
                
                Box(
                    modifier = Modifier
                        .width(45.dp)
                        .height(45.dp)
                        .padding(end = 16.dp)
                        .clickable { onCloseDialogClick() },
                    contentAlignment = Alignment.TopEnd
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_dialog_close),
                        contentDescription = "",
                        modifier = Modifier.padding(top = 15.dp),
                        tint = Color(parseColor(club.accentColor4))
                    )
                }
            }
        }
    }
}

@Composable
fun SubscriptionShortDialog(
    clubVO: ClubVO,
    email: String,
    onSubscribeClick: (String) -> Unit,
    onDismissClick: () -> Unit
) {
    Surface {
        Dialog(onDismissRequest = { }) {
            SubscribeView(
                onSubscribeClick = onSubscribeClick,
                modifier = Modifier,
                club = clubVO,
                isDialog = true,
                mEmail = email,
                onCloseDialogClick = { onDismissClick() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UncorrectedEmail() {
    val email = remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 20.dp)
            .border(
                width = 1.dp,
                color = Color(0xFFF28187),
                shape = RoundedCornerShape(size = 8.dp)
            )
    ) {
        TextField(
            value = email.value,
            onValueChange = {
                email.value = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            textStyle = textStyle,
            placeholder = {
                Text(
                    text = "Email",
                    style = textStyle.copy(color = Color(0xFF787878), fontSize = 15.sp),
                )
            },
            label = null,
            shape = RoundedCornerShape(4.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.White, textColor = textStyle.color
            ),
            keyboardOptions = KeyboardOptions()
        )
        Row(
            modifier = Modifier
                .background(
                    Color(0xFFF28187),
                    shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp)
                )
                .height(28.dp)
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Это поле является обязательным",
                style = textStyle.copy(
                    fontSize = 12.sp,
                    lineHeight = 18.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    color = Color.White,
                )
            )

        }
    }
}

private fun isValidEmail(email: String): Boolean {
    val emailRegex = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()
    return email.matches(emailRegex)
}
