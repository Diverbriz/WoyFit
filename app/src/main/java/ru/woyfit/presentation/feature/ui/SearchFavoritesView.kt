package ru.neopharm.clubs.feature.ui.compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.woyfit.R
import ru.woyfit.presentation.feature.ui.custom_theme.textStyle
import ru.woyfit.presentation.feature.ui.theme.Gray
import ru.woyfit.presentation.feature.ui.theme.PaleGray

@OptIn(ExperimentalComposeUiApi::class)
@ExperimentalFoundationApi
@Composable
fun SearchFavoritesView(
    modifier: Modifier,
    onSearchRequestChanged: (String) -> Unit
) {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    Box(
        modifier = modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = text,
            onValueChange = {
                text = it
                onSearchRequestChanged(text)
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }),
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = PaleGray, shape = RoundedCornerShape(8.dp)),
            textStyle = textStyle.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.W400,
                color = Gray
            ),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.search_title),
                    style = textStyle.copy(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = Gray
                    )
                )
            },
            trailingIcon = {
                if (text.isEmpty()) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_search),
                        contentDescription = "search icon"
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_cross_grey),
                        contentDescription = "clear search icon",
                        modifier = Modifier.clickable {
                            text = ""
                            onSearchRequestChanged(text)
                        }
                    )
                }
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                backgroundColor = Color.White,
                textColor = textStyle.color
            ),
            singleLine = true
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Preview(showBackground = true)
@Composable
fun PreviewSearchTextField() {
    SearchFavoritesView(modifier = Modifier, onSearchRequestChanged = {})
}