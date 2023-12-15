package ru.woyfit.presentation.feature.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.woyfit.domain.club.vo.ClubVO
import ru.woyfit.presentation.feature.ui.theme.TextSecondary
import ru.woyfit.presentation.feature.ui.theme.TextTitle


@Composable
fun ClubTitle(
    club: ClubVO
) {
    Column(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .padding(start = 16.dp, end = 16.dp, top = 28.dp, bottom = 24.dp),
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = club.title,
            fontSize = 24.sp,
            fontWeight = FontWeight.W400,
            color = TextTitle
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = club.description,
            fontSize = 16.sp,
            fontWeight = FontWeight.W400,
            color = TextSecondary
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewClubTitle() {
    val club = ClubVO(
        id = 0,
        title = "Я — мама",
        description = "Клуб для молодых матерей, а также ждущих ребенка и планирующих беременность",
        image = "https://stolichki.ru/1_1431768597.jpg",
        color = "#E0C98C",
        categories = emptyList()
    )
    ClubTitle(club = club)
}