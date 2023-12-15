package ru.woyfit.presentation.feature.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.delay
import ru.woyfit.R
import ru.neopharm.clubs.domain.FakeVOs
import ru.woyfit.domain.club.vo.ClubVO
import ru.neopharm.clubs.feature.ui.compose.custom_theme.ExtendedJetTheme

import ru.neopharm.clubs.feature.ui.compose.custom_theme.TextTitle
import ru.woyfit.presentation.feature.ui.custom_theme.MainTheme

@Composable
fun SubscriptionDialogSuccessSimple(
    club: ClubVO,
    onCloseDialogClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = { onCloseDialogClick.invoke() }) {
        LaunchedEffect(Unit) {
            delay(2000)
            onCloseDialogClick.invoke()
        }
        Surface(
            modifier = modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp)
        ) {
            Box(
                modifier = Modifier.background(Color(parseColor(club.accentColor3)))
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        IconButton(
                            modifier = Modifier
                                .size(25.dp),
                            onClick = onCloseDialogClick
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_dialog_close),
                                contentDescription = "",
                                modifier = Modifier.padding(top = 15.dp),
                            )
                        }

                    }

                    Row(
                        modifier =Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = stringResource(id = R.string.subscription_success_title),
                            style = ExtendedJetTheme
                                .typography.heading.copy(
                                    fontSize = 19.sp,
                                    lineHeight = 24.7.sp,
                                    color = TextTitle,
                                ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(top = 30.dp, bottom = 50.dp)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SubscriptionDialogSuccessSimplePreview() {
    MainTheme {
        SubscriptionDialogSuccessSimple(
            club = FakeVOs.getClubsVO()[0],
            onCloseDialogClick = {}
        )
    }
}